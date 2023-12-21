package com.Maxim.repository.jdbc;

import com.Maxim.model.Writer;
import com.Maxim.repository.WriterRepository;
import com.Maxim.dbutils.CrudOperation;
import com.Maxim.repository.jdbc.mappingUtils.MappingUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCWriterRepositoryImpl implements WriterRepository {

    private String tableName = "writer";
    private CrudOperation crudOperation = new CrudOperation();

    @Override
    public Writer getById(Integer writerId) {

        try {
            ResultSet resultSet = crudOperation.selectRowQuery(String.format("SELECT *\n" +
                    "FROM writer\n" +
                    "LEFT JOIN post ON post.writerId = writer.id\n" +
                    "LEFT JOIN post_labels ON post.id = post_labels.postid\n" +
                    "LEFT JOIN label ON post_labels.labelid = label.id where writer.id = %d;\n",writerId));

            return mapResultSetToWriter(resultSet).get(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Writer> getAll() {
        try {
            ResultSet resultSet = crudOperation.selectRowQuery("SELECT *\n" +
                    "FROM writer\n" +
                    "LEFT JOIN post ON post.writerId = writer.id\n" +
                    "LEFT JOIN post_labels ON post.id = post_labels.postid\n" +
                    "LEFT JOIN label ON post_labels.labelid = label.id ;\n");
            return mapResultSetToWriter(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Writer save(Writer writer) {
        HashMap<String, Object> newWriterParams = new HashMap<>();
        newWriterParams.put("firstName", writer.getFirstName());
        newWriterParams.put("lastName", writer.getLastName());

        try {
            ResultSet resultSet = crudOperation.insert(tableName, newWriterParams);
            if (resultSet.next()) {
                Integer writerId = resultSet.getInt(1);
                writer.setId(writerId);
            }
            return writer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Writer update(Writer updateWriter) {
        HashMap<String, Object> updateWriterParams = new HashMap<>();

        if (updateWriter.getFirstName() != null) {
            updateWriterParams.put("firstName", updateWriter.getFirstName());
        } else if (updateWriter.getLastName() != null) {
            updateWriterParams.put("lastName", updateWriter.getLastName());
        }

        crudOperation.updateById(tableName, updateWriterParams, updateWriter.getId());
        System.out.print("writer успешно обновлен\n");
        return updateWriter;
    }

    @Override
    public void deleteById(Integer writerId) {
        try {
            crudOperation.delete(tableName, writerId);
            System.out.print("writer успешно удален");
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }
    }

    private List<Writer> mapResultSetToWriter(ResultSet resultSet) throws SQLException {
        List<Writer> writers = new ArrayList<>();
        while (resultSet.next()) {
            Integer writerId = resultSet.getInt(1);
            int postId = resultSet.getInt(4);
            int labelId = resultSet.getInt("labelId");

            Writer writer = writers.stream()
                    .filter(writer1 -> writer1.getId() == writerId)
                    .findFirst()
                    .orElse(null);
            if (writer == null) {
                writer = new Writer();
                writer.setId(writerId);
                writer.setFirstName(resultSet.getString("firstName"));
                writer.setLastName(resultSet.getString("lastName"));
                writers.add(writer);
            }
            MappingUtils.addPostAndLabelToWriter(resultSet, writer, postId, labelId);
        }
        return writers;
    }
}
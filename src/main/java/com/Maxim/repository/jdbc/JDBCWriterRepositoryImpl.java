package com.Maxim.repository.jdbc;

import com.Maxim.model.Post;
import com.Maxim.model.Writer;
import com.Maxim.crud_data_base.base.Connector;
import com.Maxim.repository.WriterRepository;
import com.Maxim.crud_data_base.crud_operation.CrudOperation;
import com.Maxim.service.PostService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCWriterRepositoryImpl implements WriterRepository {

    private String tableName = "writer";
    private CrudOperation crudOperation = new CrudOperation();

    @Override
    public Writer getById(Integer writerId) {
        try {
            return getAll().stream().filter(writer -> writer.getId() == writerId).findFirst().orElse(null);
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();

        String sqlExpression = String.format("select * FROM %s", tableName);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression);
        ) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                PostService postService = new PostService();
                List<Post> posts = postService.getAllPosts().stream().filter(post -> post.getWriterId() == id).collect(Collectors.toList());

                Writer writer = new Writer();
                writer.setLastName(lastName);
                writer.setFirstName(firstName);
                writer.setId(id);
                writer.setPost(posts);

                writers.add(writer);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (writers.isEmpty()){
            return null;
        } else {
            return writers;
        }

    }

    @Override
    public void save(Writer writer) {
        HashMap<String, Object> newWriterParams = new HashMap<>();

        newWriterParams.put("id", writer.getId());
        newWriterParams.put("firstName", writer.getFirstName());
        newWriterParams.put("lastName", writer.getLastName());

        crudOperation.insert(tableName, newWriterParams);
        System.out.print("writer успешно сохранен, " + "id = " + writer.getId());
    }

    @Override
    public void update(Writer updateWriter) {
        HashMap<String, Object> updateWriterParams = new HashMap<>();

        updateWriterParams.put("id", updateWriter.getId());
        updateWriterParams.put("firstName", updateWriter.getFirstName());
        updateWriterParams.put("lastName", updateWriter.getLastName());

        if (getAll().stream().anyMatch(writer -> writer.getId() == updateWriter.getId())) {
            crudOperation.updateById(tableName, updateWriterParams, updateWriter.getId());
            System.out.print("writer успешно обновлен");

        } else {
            System.out.print("укзанного id нет в таблице");
        }
    }

    @Override
    public void deleteById(Integer writerId) {
        try {
            crudOperation.delete(tableName, String.format("where id = %s", writerId));
            System.out.print("writer успешно удален");
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }
    }
}

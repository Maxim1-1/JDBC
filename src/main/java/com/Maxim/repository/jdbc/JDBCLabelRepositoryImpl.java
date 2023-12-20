package com.Maxim.repository.jdbc;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.repository.LabelRepository;
import com.Maxim.dbutils.Connector;
import com.Maxim.dbutils.CrudOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCLabelRepositoryImpl implements LabelRepository {

    private CrudOperation crudOperation = new CrudOperation();
    private String tableName = "label";

    @Override
    public Label getById(Integer id) {
        try {
            ResultSet resultSet = crudOperation.selectById(tableName, id);
            return mapResultSetToLabel(resultSet).get(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Label> getAll() {
        try {
            ResultSet resultSet = crudOperation.selectAll(tableName);
            return mapResultSetToLabel(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Label save(Label label) throws SQLException {

        HashMap<String, Object> newWriterParams = new HashMap<>();
        newWriterParams.put("name", label.getName());

        try {
            ResultSet resultSet = crudOperation.insert(tableName, newWriterParams);
            if (resultSet.next()) {
                Integer writerId = resultSet.getInt(1);
                label.setId(writerId);
            }
            return label;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Label update(Label label) {
        HashMap<String, Object> updateWriterParams = new HashMap<>();
        if (label.getName()!=null){
            updateWriterParams.put("name", label.getName());
        }
        crudOperation.updateById(tableName, updateWriterParams, label.getId());
        System.out.print("label успешно обновлен\n");
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            crudOperation.delete(tableName, id);
            System.out.print("label успешно удален\n");
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }

    }

    private List<Label> mapResultSetToLabel(ResultSet resultSet) throws SQLException {
        List<Label> labels = new ArrayList<>();
        while (resultSet.next()) {
            Label label = new Label();
            label.setId(resultSet.getInt("id"));
            label.setName(resultSet.getString("name"));
            labels.add(label);
        }
        return labels;
    }


}

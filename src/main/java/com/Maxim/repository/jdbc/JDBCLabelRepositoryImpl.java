package com.Maxim.repository.jdbc;

import com.Maxim.model.Label;
import com.Maxim.repository.LabelRepository;
import com.Maxim.crud_data_base.base.Connector;
import com.Maxim.crud_data_base.crud_operation.CrudOperation;

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
            return getAll().stream()
                    .filter(label -> label.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Label> getAll() {

        List<Label> labels = new ArrayList<>();

        String sqlExpression = String.format("select * FROM %s", tableName);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String labelName = resultSet.getString("name");

                Label label = new Label();
                label.setId(id);
                label.setName(labelName);
                labels.add(label);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return labels;
    }

    @Override
    public void save(Label label) {
        HashMap<String, Object> newLabelParams = new HashMap<>();

        newLabelParams.put("id", label.getId());
        newLabelParams.put("name", label.getName());

        crudOperation.insert(tableName, newLabelParams);
        System.out.print("label успешно сохранен, " + "id = " + label.getId());
    }

    @Override
    public void update(Label label) {
        HashMap<String, Object> updateLabelParams = new HashMap<>();

        updateLabelParams.put("id", label.getId());
        updateLabelParams.put("name", label.getName());

        if (getAll().stream().anyMatch(label1 -> label1.getId()==label.getId())) {
            crudOperation.updateById(tableName, updateLabelParams, label.getId());
            System.out.print("label успешно обновлен");
        } else {
            System.out.print("укзанного id нет в таблице");
        }

    }

    @Override
    public void deleteById(Integer id) {
        try {
            crudOperation.delete(tableName, String.format("where id = %s", id));
            System.out.print("label успешно удален");

        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }

    }
}

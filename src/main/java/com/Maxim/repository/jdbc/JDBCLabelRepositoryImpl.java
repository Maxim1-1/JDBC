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

    //    @Override
//    public Label save(Label label) {
//        HashMap<String, Object> newLabelParams = new HashMap<>();
//
//        newLabelParams.put("id", label.getId());
//        newLabelParams.put("name", label.getName());
//
//        crudOperation.insert(tableName, newLabelParams);
//        System.out.print("label успешно сохранен, " + "id = " + label.getId());
//        return label;
//    }
    @Override
    public Label save(Label label) throws SQLException {

        PreparedStatement preparedStatement = Connector.getPreparedStatementWithGeneratedKeys("INSERT...");
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            Integer generatedId = resultSet.getInt(1);
            label.setId(generatedId);
        }

        return label;
    }

    @Override
    public Label update(Label label) {
        HashMap<String, Object> updateLabelParams = new HashMap<>();

        updateLabelParams.put("id", label.getId());
        updateLabelParams.put("name", label.getName());

        if (getAll().stream().anyMatch(label1 -> label1.getId() == label.getId())) {
            crudOperation.updateById(tableName, updateLabelParams, label.getId());

//   crudOperation.updateById -> Resultset -> mapping ->
            System.out.print("label успешно обновлен");
        } else {
            System.out.print("укзанного id нет в таблице");
        }

        return label;
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

    private Post mapResultSetToWriter(ResultSet resultSet) throws SQLException {

        Integer id = resultSet.getInt("id");
        String content = resultSet.getString("content");
        String created = resultSet.getString("created");
        String updated = resultSet.getString("updated");
        String status = resultSet.getString("status");
        Integer writerId = resultSet.getInt("writerId");


        Post post = new Post();

        post.setId(id);
        post.setLabels(null);
        post.setContent(content);
        post.setCreated(created);
        post.setUpdated(updated);
        post.setPostStatus(PostStatus.valueOf(status));
//        post.setWriterId(null);

        return post;
    }

}

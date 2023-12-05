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
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Хранилище пусто");
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
                Integer postId = resultSet.getInt("postId");
                String labelName = resultSet.getString("name");

                Label label = new Label();
                label.setId(id);
                label.setName(labelName);
                label.setPostId(postId);
                labels.add(label);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return labels;
    }

    @Override
    public void save(Label label) {
        try {
            crudOperation.insert(tableName,"id,name,postId",String.format("%s, '%s',%s",label.getId(),label.getName(),label.getPostId()));
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Хранилище пусто");
        }


    }

    @Override
    public void update(Label label) {

        HashMap<String,Object> updatePostParams = new HashMap<>();

        updatePostParams.put("id",label.getId());
        updatePostParams.put("name",label.getName());

        crudOperation.updateById(tableName,updatePostParams,label.getId());

//        crudOperation.update(tableName,String.format("set name = '%s'",label.getName()),String.format("where id = %s",label.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        crudOperation.delete(tableName,String.format("where id = %s",id));
    }
}

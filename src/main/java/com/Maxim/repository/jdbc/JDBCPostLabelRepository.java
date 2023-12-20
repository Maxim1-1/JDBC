package com.Maxim.repository.jdbc;

import com.Maxim.dbutils.Connector;
import com.Maxim.dbutils.CrudOperation;
import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.service.LabelService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCPostLabelRepository {

    private final CrudOperation crudOperation = new CrudOperation();
    private final String tableName = "post_labels";

    public List<Label> getPostsById(Integer postId){

        List<Label> labels = new ArrayList<>();
        LabelService labelService = new LabelService();

        String sqlExpression = String.format("SELECT labelId FROM %s where postId=%d;", tableName, postId);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                Integer id = resultSet.getInt("labelId");

                Label label = labelService.getLabelById(id);
                labels.add(label);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return labels;
    }

    public void save(Post post, Label label){
        HashMap<String, Object> newLabelParams = new HashMap<>();

        newLabelParams.put("postId", post.getId());
        newLabelParams.put("labelId", label.getId());

//        crudOperation.insert(tableName, newLabelParams);
    }

}

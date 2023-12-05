package com.Maxim.repository.jdbc;

import com.Maxim.crud_data_base.base.Connector;
import com.Maxim.crud_data_base.crud_operation.CrudOperation;
import com.Maxim.model.Label;
import com.Maxim.service.LabelService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
}

package com.Maxim.crud_data_base.crud_operation;

import com.Maxim.crud_data_base.base.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CrudOperation {


    public void insert(String tableName, HashMap<String,Object> parametersAndValues) {

        HashMap<String,String> formatedColimnsAndValues = CrudUtils.insertUtil(parametersAndValues);

        String sqlExpression = String.format("insert into %s (%s) value (%s)", tableName, formatedColimnsAndValues.get("columns"), formatedColimnsAndValues.get("values"));

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {

            int count = 1;
            for (Map.Entry<String, Object> entry : parametersAndValues.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Integer) {
                    statement.setInt(count++, (Integer) value);
                } else {
                    statement.setString(count++, String.valueOf(value));
                }
            }
             statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateById(String tableName, HashMap<String, Object> parameters, Integer id) {
        String parametersString = CrudUtils.updateUtil(parameters);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(String.format("update %s set %s  where id = %d", tableName,parametersString,id)))
        {
            int count = 1;
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Integer) {
                    statement.setInt(count++, (Integer) value);
                } else {
                    statement.setString(count++, String.valueOf(value));
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void delete(String tableName, String condition) {
        String sqlExpression = String.format("delete FROM  %s  %s", tableName, condition);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

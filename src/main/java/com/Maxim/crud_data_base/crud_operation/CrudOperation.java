package com.Maxim.crud_data_base.crud_operation;

import com.Maxim.crud_data_base.base.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudOperation {


    public void insert(String tableName, String nameColumns, String values) {

//        HashMap<String, String> data = CrudUtils.convertColumnAndValuesForExpression(nameColumns, values);

        String sqlExpression = String.format("insert into %s (%s) value (%s)", tableName, nameColumns, values);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(String tableName, String parameters, String condition) {

        String sqlExpression = String.format("update %s %s %s", tableName, parameters, condition);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {
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

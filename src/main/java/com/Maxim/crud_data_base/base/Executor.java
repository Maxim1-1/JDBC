package com.Maxim.crud_data_base.base;

import java.sql.*;

public class Executor {
    private final Connection connector;

    {
        try {
            connector = Connector.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeExpression(String SQLExpression) {

        try (Statement statement = connector.createStatement()) {
            statement.executeUpdate(SQLExpression);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getResultExpression(String SQLExpression) throws SQLException {

        PreparedStatement statement = connector.prepareStatement(SQLExpression);
//        return statement.executeQuery();
        statement.execute();
        return  statement.getResultSet();
    }


//    public PreparedStatement getPreparedStatement()  {
//
//        PreparedStatement statement = null;
//        try {
//            statement = connector.prepareStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return statement;
//    }
//


}

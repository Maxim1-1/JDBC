package com.Maxim.dbutils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CrudOperation {

    public ResultSet selectRowQuery(String sqlExpression) throws SQLException {
        return select(sqlExpression);
    }

    public ResultSet selectAll(String mainTable, String subTable, String mainForeignKey, String subForeignKey) throws SQLException {
        String sqlExpression = String.format("SELECT * FROM %s left join %s on %s.%s=%s.%s;", mainTable, subTable, mainTable, mainForeignKey, subTable, subForeignKey);
        return select(sqlExpression);
    }

    public ResultSet selectAll(String tableName) throws SQLException {
        String sqlExpression = String.format("select * FROM %s", tableName);
        return select(sqlExpression);
    }

    public ResultSet selectById(String tableName,Integer id) throws SQLException {
        String sqlExpression = String.format("select * FROM %s where id = %s", tableName, id);
        return select(sqlExpression);
    }

    private ResultSet select(String sqlExpression) throws SQLException {

        PreparedStatement statement = Connector.getPreparedStatementWithGeneratedKeys(sqlExpression);

        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet insert(String tableName, HashMap<String, Object> parametersAndValues) throws SQLException {

        HashMap<String, String> formatColumnsAndValues = CrudUtils.insertUtil(parametersAndValues);

        String sqlExpression = String.format("insert into %s (%s) value (%s)", tableName, formatColumnsAndValues.get("columns"), formatColumnsAndValues.get("values"));

        PreparedStatement statement = Connector.getPreparedStatementWithGeneratedKeys(sqlExpression);

        PreparedStatement statementWithParameters = CrudUtils.fillingStatementWithParameters(statement, parametersAndValues);
        statementWithParameters.executeUpdate();

        return statementWithParameters.getGeneratedKeys();

    }


    public void updateById(String tableName, HashMap<String, Object> parametersAndValues, Integer id) {
        String parametersString = CrudUtils.updateUtil(parametersAndValues);

        String sqlExpression = String.format("update %s set %s  where id = %d", tableName, parametersString, id);
        try (PreparedStatement statement = Connector.getPreparedStatement(sqlExpression)) {

            PreparedStatement statementWithParameters = CrudUtils.fillingStatementWithParameters(statement, parametersAndValues);
            statementWithParameters.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(String tableName, Integer deleteId) {
        String sqlExpression = String.format("delete FROM  %s  where id = ?",tableName);
        try (PreparedStatement statement = Connector.getPreparedStatement(sqlExpression)) {
            statement.setInt(1,deleteId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

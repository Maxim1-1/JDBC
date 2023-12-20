package com.Maxim.dbutils;

import com.Maxim.utils.CredentialsUtils;

import java.sql.*;


public class Connector {

//    public static Connection getConnect() throws SQLException {
//
//        String host = CredentialsUtils.getCredential("host");
//        String login = CredentialsUtils.getCredential("login");
//        String password = CredentialsUtils.getCredential("password");
//        String db = CredentialsUtils.getCredential("db");
//
//        return DriverManager.getConnection(host + db, login, password);
//
//    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnect().prepareStatement(sql);
    }

    public static PreparedStatement getPreparedStatementWithGeneratedKeys(String sql) throws SQLException {
        return getConnect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    private Connector() {
    }

    public static Connection getConnect() throws SQLException {
        return ConnectionHolder.getConnection();
    }

    private static class ConnectionHolder {
        private static final String host = CredentialsUtils.getCredential("host");
        private static final String login = CredentialsUtils.getCredential("login");
        private static final String password = CredentialsUtils.getCredential("password");
        private static final String db = CredentialsUtils.getCredential("db");

        private static final Connection connection;

        static {
            try {
                connection = DriverManager.getConnection(host + db, login, password);
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при создании подключения к базе данных", e);
            }
        }

        public static Connection getConnection() {
            return connection;
        }
    }
}
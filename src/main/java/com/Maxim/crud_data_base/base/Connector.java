package com.Maxim.crud_data_base.base;

import com.Maxim.utils.CredentialsUtils;

import java.sql.*;


public class Connector {

    public static Connection getConnect() throws SQLException {

        String host = CredentialsUtils.getCredential("host");
        String login = CredentialsUtils.getCredential("login");
        String password = CredentialsUtils.getCredential("password");
        String db = CredentialsUtils.getCredential("db");

        return DriverManager.getConnection(host+db, login, password);

    }
}

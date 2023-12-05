package com.Maxim.crud_data_base.base;

import com.Maxim.utils.CredentialsUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connector {

    public static Connection getConnect() throws SQLException {

        String host = CredentialsUtils.getCredential("host");
        String login = CredentialsUtils.getCredential("login");
        String password = CredentialsUtils.getCredential("password");
        String db = CredentialsUtils.getCredential("db");

        return DriverManager.getConnection(host+db, login, password);

    }
}

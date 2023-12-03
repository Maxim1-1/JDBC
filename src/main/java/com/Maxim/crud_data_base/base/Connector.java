package com.Maxim.crud_data_base.base;

import java.sql.*;

public class Connector {

    public static Connection getConnect() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

    }
}

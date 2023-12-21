package com.Maxim.liquibase;

import com.Maxim.dbutils.Connector;

import com.Maxim.utils.CredentialsUtils;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseRunner {
    private static final String host = CredentialsUtils.getCredential("host");
    private static final String login = CredentialsUtils.getCredential("login");
    private static final String password = CredentialsUtils.getCredential("password");
    private static final String db = CredentialsUtils.getCredential("db");

    public void run() {

        try (Connection connection = DriverManager.getConnection(host + db, login, password);) {

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            liquibase.Liquibase liquibase = new liquibase.Liquibase("db/changelog/changeset/create.yaml",
                    new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.Maxim.liquibase;

import com.Maxim.dbutils.Connector;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.SQLException;

public class LiquibaseRunner {
    public void run() {

        try ( Connection connection = Connector.getConnect()){
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

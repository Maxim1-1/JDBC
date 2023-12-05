package com.Maxim;

import com.Maxim.command_user_handler.UserHandler;
import com.Maxim.crud_data_base.base.Connector;
import com.Maxim.crud_data_base.crud_operation.CrudOperation;
import com.Maxim.crud_data_base.crud_operation.CrudUtils;
import com.Maxim.utils.CredentialsUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        LiquibaseRunner liquibase = new LiquibaseRunner();
//        liquibase.run();

        while (true) {
            System.out.println("Для выхода еще раз введите exit, для продолжения введите любой символ");

            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            } else {
                UserHandler.getCommandConsole();
            }

        }
    }
}
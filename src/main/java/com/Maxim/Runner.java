package com.Maxim;

import com.Maxim.dbutils.Connector;
import com.Maxim.view.DispatcherView;
import com.Maxim.liquibase.LiquibaseRunner;

import java.sql.SQLException;
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
                DispatcherView.getCommandConsole();
            }

        }

        try {
            Connector.getConnect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

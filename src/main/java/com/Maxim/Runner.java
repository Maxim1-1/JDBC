package com.Maxim;

import com.Maxim.command_user_handler.UserHandler;
import com.Maxim.liquibase.LiquibaseRunner;

import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LiquibaseRunner liquibase = new LiquibaseRunner();
        liquibase.run();

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

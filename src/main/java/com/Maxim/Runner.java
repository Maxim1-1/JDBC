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

//        1 метод
        HashMap<String, Object> s = new HashMap<>();
        s.put("id", 1);
        s.put("name", "test");



//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, Object> entry : s.entrySet()) {
//            sb.append(entry.getKey())
//                    .append(" = ")
//                    .append("?")
//                    .append(", ");
//        }
//
//        String result = sb.substring(0, sb.length() - ", ".length());


//        2 метод
//        try (Connection connector = Connector.getConnect();
//
////             String ss = "update"+ "?" + "set ";
////             update t set id = ?, content = ?  where = t
//
//             PreparedStatement statement = connector.prepareStatement(String.format("update label set %s  where id = 1",result))) {
//
//            int count=1;
//            for (Map.Entry<String, Object> entry : s.entrySet()) {
//
//                String key = entry.getKey();
//                Object value = entry.getValue();
//
//                if (value instanceof Integer) {
//                    statement.setInt(count++, (Integer) value);
//                } else if (value instanceof String){
//                    statement.setString(count++,String.valueOf(value));
//                } else {
////                    enum
//                }
//
//            }
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//

//
//
//












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
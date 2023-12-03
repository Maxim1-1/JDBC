package com.Maxim.crud_data_base.crud_operation;

import java.util.HashMap;

public class CrudUtils {
    public static HashMap<String, String> convertColumnAndValuesForExpression(String[] nameColumns, String[] values) {

        HashMap<String, String> data = new HashMap<>();
        String columns = String.join(", ", nameColumns);
        String val = String.join(", ", values);

        StringBuilder result = new StringBuilder();
        String[] parts = val.split(",");
        for (String part : parts) {
            part = part.trim();
            if (part.matches("\\d+")) {
                result.append(part);
            } else {
                result.append("'").append(part).append("'");
            }
            result.append(", ");
        }
        String readyValues = result.toString();
        if (readyValues.endsWith(", ")) {
            readyValues = readyValues.substring(0, readyValues.length() - 2);
        }


        data.put("columns", columns);
        data.put("values", readyValues);
        return data;
    }
}

package com.Maxim.crud_data_base.crud_operation;

import java.util.HashMap;
import java.util.Map;

public class CrudUtils {


    public static String updateUtil(HashMap<String, Object> parametrsValue) {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : parametrsValue.entrySet()) {
            sb.append(entry.getKey())
                    .append(" = ")
                    .append("?")
                    .append(", ");
        }
        String result = sb.substring(0, sb.length() - ", ".length());
        return result;
    }

    public static HashMap<String, String> insertUtil(HashMap<String, Object> parametrsValue) {

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Map.Entry<String, Object> entry : parametrsValue.entrySet()) {
            columns.append(entry.getKey())
                    .append(", ");

            values
                    .append("?")
                    .append(", ");
        }
        String columnsFormated = columns.substring(0, columns.length() - ", ".length());
        String valuesFormated = values.substring(0, values.length() - ", ".length());

        HashMap<String, String> result = new HashMap<>();
        result.put("columns", columnsFormated);
        result.put("values", valuesFormated);
        return result;
    }


}

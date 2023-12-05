package com.Maxim.crud_data_base.crud_operation;

import java.util.HashMap;
import java.util.Map;

public class CrudUtils {


    public static String updateUtil(HashMap<String, Object> parametrsValue){

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

    public static String insertUtil(HashMap<String, Object> parametrsValue){

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : parametrsValue.entrySet()) {
            sb.append(entry.getKey())
                    .append(", ");
        }
        String result = sb.substring(0, sb.length() - ", ".length());
        return result;
    }


}

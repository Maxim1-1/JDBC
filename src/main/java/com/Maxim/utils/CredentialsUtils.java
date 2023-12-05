package com.Maxim.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class CredentialsUtils {
    public static String getCredential (String credentialName){
        FileReader reader = null;
        try {
            Properties properties = new Properties();

            reader = new FileReader(String.valueOf(Paths.get("src", "main", "resources", "credentials.properties")));
            properties.load(reader);

            return (properties.getProperty(credentialName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

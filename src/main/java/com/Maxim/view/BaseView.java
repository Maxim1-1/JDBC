package com.Maxim.view;

import java.util.Scanner;

public class BaseView {

    public Integer getIdFromConsole(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }
}

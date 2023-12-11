package com.Maxim.view;

import com.Maxim.model.Label;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LabelView extends BaseView{
    private HashMap<String,String > userDataFromConsole = new HashMap();
    private Scanner scanner = new Scanner(System.in);

    public HashMap<String,String> create() {
        String labelName;

        System.out.println("Введите имя заголовка :");
        labelName = scanner.nextLine();
        userDataFromConsole.put("labelName",labelName);
        return userDataFromConsole;

    }

    public void getLabelById(Label label){
        System.out.println(String.format("id = %s , label name = %s",label.getId(),label.getName()));
    }

    public void getAllLabels(List<Label> labels){
        for (Label label:labels) {
            System.out.print(String.format("id: %s, name: %s\n",label.getId(),label.getName()));
        }

    }

    public HashMap<String,String> updateLabelById(){
        System.out.println("Введите имя нового заголовка :");

        String labelName = scanner.nextLine();
        userDataFromConsole.put("labelName",labelName);

        return userDataFromConsole;
    }

}

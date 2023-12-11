package com.Maxim.controller;

import com.Maxim.model.Label;
import com.Maxim.service.LabelService;
import com.Maxim.view.LabelView;

import java.util.HashMap;

public class LabelController {
    private  LabelService labelService = new LabelService();
    private  LabelView labelView = new LabelView();


    public void save() {
        Label label = new Label();
        HashMap<String, String> dataFromConsole = labelView.create();
        label.setName(dataFromConsole.get("labelName"));
        labelService.createLabel(label);
    }


    public void getLabelById() {
        Integer labelId = labelView.getIdFromConsole("Введите label id");
        Label label = labelService.getLabelById(labelId);
        labelView.getLabelById(label);
    }

    public void getAllLabels() {
        labelView.getAllLabels(labelService.getAllLabels());
    }

    public void deleteLabelById() {
        Integer labelId = labelView.getIdFromConsole("Введите label id");
        labelService.deleteLabelById(labelId);
    }

    public void updateLabelById() {

       Integer labelId = labelView.getIdFromConsole("Введите label id для обновления");
       String newNameLabel = labelView.updateLabelById().get("labelName");

       Label label = new Label();
       label.setId(labelId);
       label.setName(newNameLabel);

       labelService.updateLabel(label);

    }

}

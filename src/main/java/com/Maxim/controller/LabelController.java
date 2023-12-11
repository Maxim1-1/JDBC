package com.Maxim.controller;

import com.Maxim.model.Label;
import com.Maxim.service.LabelService;
import com.Maxim.view.LabelView;

import java.util.HashMap;
import java.util.List;

public class LabelController {
    private  LabelService labelService = new LabelService();
    private  LabelView labelView = new LabelView();


    public void save(HashMap<String, String> dataFromConsole) {
        Label label = new Label();
        label.setName(dataFromConsole.get("labelName"));
        labelService.saveLabel(label);
    }


    public Label getLabelById(Integer labelId) {
        return labelService.getLabelById(labelId);
    }

    public List<Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    public void deleteLabelById(Integer labelId) {
        labelService.deleteLabelById(labelId);
    }

    public void updateLabelById(Integer labelId,String newNameLabel) {
       Label label = new Label();
       label.setId(labelId);
       label.setName(newNameLabel);

       labelService.updateLabel(label);

    }

}

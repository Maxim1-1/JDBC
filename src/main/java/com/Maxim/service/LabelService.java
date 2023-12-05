package com.Maxim.service;

import com.Maxim.model.Label;
import com.Maxim.repository.jdbc.JDBCLabelRepositoryImpl;

import java.util.List;

public class LabelService {

    private static  JDBCLabelRepositoryImpl jdbc = new JDBCLabelRepositoryImpl();

    public void createLabel(Label label) {
        jdbc.save(label);
    }

    public Label getLabelById(Integer labelId){
        return jdbc.getById(labelId);
    }

    public List<Label> getAllLabels() {
        return jdbc.getAll();
    }

    public void updateLabel(Label label) {
        jdbc.update(label);
    }

    public void deleteLabelById (Integer id){
        jdbc.deleteById(id);
    }
}

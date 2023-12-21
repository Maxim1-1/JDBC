package com.Maxim.service;

import com.Maxim.model.Label;
import com.Maxim.repository.jdbc.JDBCLabelRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class LabelService {

    private final JDBCLabelRepositoryImpl jdbc = new JDBCLabelRepositoryImpl();

    public Label saveLabel(Label label) {
        return jdbc.save(label);
    }

    public Label getLabelById(Integer labelId) {
        return jdbc.getById(labelId);
    }

    public List<Label> getAllLabels() {
        return jdbc.getAll();
    }

    public Label updateLabel(Label label) {
        return jdbc.update(label);
    }

    public void deleteLabelById(Integer id) {
        jdbc.deleteById(id);
    }
}

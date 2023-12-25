package com.Maxim.service;

import com.Maxim.model.Label;
import com.Maxim.repository.LabelRepository;
import com.Maxim.repository.jdbc.JDBCLabelRepositoryImpl;

import java.util.List;

public class LabelService {

    private final LabelRepository repository = new JDBCLabelRepositoryImpl();

    public Label saveLabel(Label label) {
        return repository.save(label);
    }

    public Label getLabelById(Integer labelId) {
        return repository.getById(labelId);
    }

    public List<Label> getAllLabels() {
        return repository.getAll();
    }

    public Label updateLabel(Label label) {
        return repository.update(label);
    }

    public void deleteLabelById(Integer id) {
        repository.deleteById(id);
    }
}

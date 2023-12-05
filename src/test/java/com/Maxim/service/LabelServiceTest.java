package com.Maxim.service;

import com.Maxim.model.Label;
import com.Maxim.repository.jdbc.JDBCLabelRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LabelServiceTest {


    @Test
    public void createLabel() {
        LabelService labelService = Mockito.mock(LabelService.class);
    }
    @Test
    public void getLabelById() {
        LabelService labelService = Mockito.mock(LabelService.class);

        Label expectedLabel = new Label();

        expectedLabel.setId(1);
        expectedLabel.setName("test");

        Mockito.when(labelService.getLabelById(1)).thenReturn(expectedLabel);
    }

    @Test
    public void getAllLabels() {
    }

    @Test
    public void updateLabel() {
    }

    @Test
    public void deleteLabelById() {
    }
}
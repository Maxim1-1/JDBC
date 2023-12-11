package com.Maxim.LabelService;

import com.Maxim.controller.LabelController;
import com.Maxim.model.Label;
import com.Maxim.service.LabelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LabelServiceTest {

    @Mock
    private LabelService labelService = new LabelService();

    @Mock
    private Label labelMock = new Label();
    @InjectMocks
    private LabelController labelController = new LabelController();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getLabelByIdTest () {

        Label label = new Label();
        label.setId(1);
        label.setName("test1");

        when(labelService.getLabelById(1)).thenReturn(label);
        Label result = labelController.getLabelById(1);

        assertEquals(label.getId(),result.getId());
        assertEquals(label.getName(),result.getName());
    }

    @Test
    void getLabelByIdTestReturnNull () {
        when(labelService.getLabelById(1)).thenReturn(null);
        Label result = labelController.getLabelById(1);
        assertNull(result);
    }

    @Test
    void getAllWriterTest () {
        Label labelFirst = new Label();
        labelFirst.setId(1);
        labelFirst.setName("test1");

        Label labelSecond = new Label();
        labelSecond.setId(2);
        labelSecond.setName("test2");

        List<Label> labels= new ArrayList<>();
        labels.add(labelFirst);
        labels.add(labelSecond);

        when(labelService.getAllLabels()).thenReturn(labels);

        List<Label> result = labelController.getAllLabels();

        assertEquals(2,result.size());

        assertEquals(labelFirst.getId(),result.get(0).getId());
        assertEquals(labelFirst.getName(),result.get(0).getName());

        assertEquals(labelSecond.getId(),result.get(1).getId());
        assertEquals(labelSecond.getName(),result.get(1).getName());

    }

    @Test
    void getAllLabelsTestReturnNull () {
        when(labelService.getAllLabels()).thenReturn(null);
        List<Label> result = labelController.getAllLabels();
        assertNull(result);
    }

    @Test
    void updateLabelByIdTest () {
        String updatedData = "test1";
        labelMock.setId(1);
        labelMock.setName(updatedData);

        labelController.updateLabelById(1,updatedData);
        verify(labelService).updateLabel(any(Label.class));
    }

    @Test
    void deleteLabelByIdTest () {
        labelController.deleteLabelById(1);
        verify(labelService).deleteLabelById(1);
    }

    @Test
    public void saveLabelTest() {
        HashMap<String, String> dataFromConsole = new HashMap<>();
        dataFromConsole.put("labelName","test1");

        labelController.save(dataFromConsole);

        verify(labelService).saveLabel(any(Label.class));
    }
}


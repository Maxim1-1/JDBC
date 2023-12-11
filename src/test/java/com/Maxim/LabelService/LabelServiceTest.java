package com.Maxim.LabelService;

import com.Maxim.controller.LabelController;
import com.Maxim.model.Label;
import com.Maxim.service.LabelService;
import com.Maxim.view.LabelView;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class LabelServiceTest {

    @Mock
    private LabelView labelView;

    @Mock
    private LabelService labelService;

    @InjectMocks
    private LabelController labelController;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    public LabelServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test () {
        Integer labelId = 1;
        Label label = new Label();
        label.setName("test");
        label.setId(1);

        when(labelView.getIdFromConsole(anyString())).thenReturn(labelId);
        when(labelService.getLabelById(labelId)).thenReturn(label);

        labelController.getLabelById();

        assertEquals(String.format("id = %s , label name = %s",label.getId(),label.getName()), systemOutRule.getLog());
    }

}

package com.Maxim.LabelService;

import com.Maxim.controller.LabelController;
import com.Maxim.controller.WriterController;
import com.Maxim.model.Writer;
import com.Maxim.service.WriterService;
import com.Maxim.view.LabelView;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class WriterServiceTest {

    @Mock
    private WriterService writerService = new WriterService();
    @InjectMocks
    private WriterController writerController = new WriterController();
    private AutoCloseable closeable;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllWriterTest () {

        Writer writerFirst = new Writer();
        writerFirst.setId(1);
        writerFirst.setFirstName("test1");
        writerFirst.setLastName("lastNameTest");

        Writer writerSecond = new Writer();
        writerSecond.setId(2);
        writerSecond.setFirstName("test2");
        writerSecond.setLastName("lastNameTest");

        List<Writer> listWriters = new ArrayList<>();
        listWriters.add(writerFirst);
        listWriters.add(writerSecond);


        when(writerService.getAllWriters()).thenReturn(listWriters);

        List<Writer> result = writerController.getAllWriter();

        assertEquals(2,result.size());

        assertEquals(writerFirst.getId(),result.get(0).getId());
        assertEquals(writerFirst.getFirstName(),result.get(0).getFirstName());
        assertEquals(writerFirst.getLastName(),result.get(0).getLastName());


        assertEquals(writerSecond.getId(),result.get(1).getId());
        assertEquals(writerSecond.getFirstName(),result.get(1).getFirstName());
        assertEquals(writerSecond.getLastName(),result.get(1).getLastName());

    }

}

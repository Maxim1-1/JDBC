package com.Maxim.LabelService;

import com.Maxim.controller.WriterController;
import com.Maxim.model.Post;
import com.Maxim.model.Writer;
import com.Maxim.service.WriterService;
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


public class WriterServiceTest {

    @Mock
    private WriterService writerService = new WriterService();

    @Mock
    private Writer writerMock = new Writer();
    @InjectMocks
    private WriterController writerController = new WriterController();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getWriterByIdTest () {

        Post post = new Post();

        post.getId();
        post.getContent();
        post.getCreated();
        post.getUpdated();
        post.getWriterId();
        post.getPostStatus();

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Writer writer = new Writer();
        writer.setId(1);
        writer.setFirstName("test1");
        writer.setLastName("lastNameTest");
        writer.setPosts(posts);

        when(writerService.getWriterById(1)).thenReturn(writer);
        Writer result = writerController.getWriterById(1);

        assertEquals(writer.getId(),result.getId());
        assertEquals(writer.getFirstName(),result.getFirstName());
        assertEquals(writer.getLastName(),result.getLastName());
        assertEquals(writer.getPosts(),result.getPosts());

    }

    @Test
    void getWriterByIdTestReturnNull () {
        when(writerService.getWriterById(1)).thenReturn(null);
        Writer result = writerController.getWriterById(1);
        assertNull(result);
    }

    @Test
    void getAllWriterTest () {

        Post post = new Post();

        post.getId();
        post.getContent();
        post.getCreated();
        post.getUpdated();
        post.getWriterId();
        post.getPostStatus();

        List<Post> posts = new ArrayList<>();
        posts.add(post);


        Writer writerFirst = new Writer();
        writerFirst.setId(1);
        writerFirst.setFirstName("test1");
        writerFirst.setLastName("lastNameTest");
        writerFirst.setPosts(posts);

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
        assertEquals(writerFirst.getPosts(),result.get(0).getPosts());


        assertEquals(writerSecond.getId(),result.get(1).getId());
        assertEquals(writerSecond.getFirstName(),result.get(1).getFirstName());
        assertEquals(writerSecond.getLastName(),result.get(1).getLastName());

    }

    @Test
    void getAllWriterTestReturnNull () {
        when(writerService.getAllWriters()).thenReturn(null);
        List<Writer> result = writerController.getAllWriter();
        assertNull(result);
    }


    @Test
    void updateWriterByIdTest () {
        HashMap<String, String> updatedData = new HashMap<>();
        updatedData.put("firstName","test1");
        updatedData.put("lastName","test2");

        Post post = new Post();
        post.getId();
        post.getContent();
        post.getCreated();
        post.getUpdated();
        post.getWriterId();
        post.getPostStatus();
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        writerMock.setId(1);
        writerMock.setFirstName("test1");
        writerMock.setLastName("lastNameTest");
        writerMock.setPosts(posts);

        when(writerService.getWriterById(1)).thenReturn(writerMock);

        writerController.updateWriterById(1,updatedData);
        verify(writerService).updateWriter(any(Writer.class));

    }

    @Test
    void deleteWriterByIdTest () {
        writerController.deleteWriterById(1);

        verify(writerService).deleteWriterById(1);
    }

    @Test
    public void saveWriterTest() {
        HashMap<String, String> dataFromConsole = new HashMap<>();
        dataFromConsole.put("firstname", "John");
        dataFromConsole.put("lastname", "Doe");

        writerController.save(dataFromConsole);

        verify(writerService).save(any(Writer.class));
    }
}


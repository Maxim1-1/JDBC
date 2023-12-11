package com.Maxim.LabelService;


import com.Maxim.controller.PostController;
import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.service.PostService;
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


public class PostServiceTest {

    @Mock
    private PostService postService = new PostService();

    @Mock
    private Post postMock = new Post();
    @InjectMocks
    private PostController postController = new PostController();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getPostByIdTest () {
        Label labelFirst = new Label();
        labelFirst.setName("test1");
        labelFirst.setId(1);
        Label labelSecond = new Label();
        labelSecond.setName("test2");
        labelSecond.setId(2);
        List<Label> labels = new ArrayList<>();
        labels.add(labelFirst);
        labels.add(labelSecond);

        Post post = new Post();

        post.setId(1);
        post.setLabels(labels);
        post.setContent("testtest");
        post.setCreated("2023-12-05");
        post.setUpdated("2023-12-05");
        post.setPostStatus(PostStatus.UNDER_REVIEW);

        when(postService.getPostById(1)).thenReturn(post);
        Post result = postController.getPostById(1);

        assertEquals(post.getId(),result.getId());
        assertEquals(post.getContent(),result.getContent());
        assertEquals(post.getPostStatus(),result.getPostStatus());
        assertEquals(post.getCreated(),result.getCreated());
        assertEquals(post.getUpdated(),result.getUpdated());
        assertEquals(post.getLabels(),result.getLabels());
    }

    @Test
    void getPostByIdTestReturnNull () {
        when(postService.getPostById(1)).thenReturn(null);
        Post result = postController.getPostById(1);
        assertNull(result);
    }

    @Test
    void getAllPostsTest () {
        Post postFirst = new Post();

        postFirst.setId(1);
        postFirst.setContent("testtest");
        postFirst.setCreated("2023-12-05");
        postFirst.setUpdated("2023-12-05");
        postFirst.setPostStatus(PostStatus.UNDER_REVIEW);

        Post postSecond = new Post();

        postSecond.setId(2);
        postSecond.setContent("testtest2");
        postSecond.setCreated("2023-12-05");
        postSecond.setUpdated("2023-12-05");
        postSecond.setPostStatus(PostStatus.UNDER_REVIEW);

        List<Post> posts= new ArrayList<>();

        posts.add(postFirst);
        posts.add(postSecond);


        when(postService.getAllPosts()).thenReturn(posts);

        List<Post> result = postController.getAllPosts();

        assertEquals(2,result.size());


        assertEquals(postFirst.getId(),result.get(0).getId());
        assertEquals(postFirst.getContent(),result.get(0).getContent());
        assertEquals(postFirst.getPostStatus(),result.get(0).getPostStatus());
        assertEquals(postFirst.getCreated(),result.get(0).getCreated());
        assertEquals(postFirst.getUpdated(),result.get(0).getUpdated());


        assertEquals(postSecond.getId(),result.get(1).getId());
        assertEquals(postSecond.getContent(),result.get(1).getContent());
        assertEquals(postSecond.getPostStatus(),result.get(1).getPostStatus());
        assertEquals(postSecond.getCreated(),result.get(1).getCreated());
        assertEquals(postSecond.getUpdated(),result.get(1).getUpdated());


    }

    @Test
    void getAllPostsTestReturnNull () {
        when(postService.getAllPosts()).thenReturn(null);
        List<Post> result = postController.getAllPosts();
        assertNull(result);
    }

    @Test
    void updatePostByIdTest () {
        HashMap<String,String> updatedData = new HashMap<>();
        updatedData.put("content","newContent");
        updatedData.put("created","newCreated");
        updatedData.put("updated","newUpdated");
        updatedData.put("status", String.valueOf(PostStatus.ACTIVE));


        when(postService.getPostById(1)).thenReturn(postMock);

        postController.updatePostById(1,updatedData);
        verify(postService).updatePostById(any(Post.class));
    }

    @Test
    void deletePostByIdTest () {
        postController.deletePostById(1);
        verify(postService).deletePostById(1);
    }

    @Test
    public void saveLabelTest() {
        HashMap<String,String> updatedData = new HashMap<>();
        updatedData.put("content","newContent");
        updatedData.put("created","newCreated");
        updatedData.put("updated","newUpdated");
        updatedData.put("status", String.valueOf(PostStatus.ACTIVE));

        postController.savePost(updatedData);
        verify(postService).savePost(any(Post.class));
    }
}


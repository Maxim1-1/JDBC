package com.Maxim.controller;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;
import com.Maxim.repository.jdbc.JDBCLabelRepositoryImpl;
import com.Maxim.service.PostService;
import com.Maxim.service.WriterService;
import com.Maxim.view.PostView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostController {

    private static PostService postService = new PostService();
    private PostView postView = new PostView();


    public void save() {
        Post post = new Post();

        HashMap<String, String> dataFromConsole = postView.create();

//        TODO id gener и время поста
        post.setId(12121);

        Writer writer = null;
        if (dataFromConsole.get("writerId") != null) {
            WriterService writerService = new WriterService();
            writer = writerService.getWriterById(Integer.valueOf(dataFromConsole.get("writerId")));
        } else {
            writer = new Writer();
            writer.setId(post.getId());
            writer.setFirstName(dataFromConsole.get("firstName"));
            writer.setLastName(dataFromConsole.get("lastName"));
            WriterService writerService = new WriterService();
            writerService.save(writer);
        }

        post.setCreated("test");
        post.setUpdated("test");
        post.setContent(dataFromConsole.get("postText"));
        post.setPostStatus(PostStatus.UNDER_REVIEW);
        post.setWriterId(writer.getId());


        Label label = new Label();
        label.setId(3533);
        label.setName(dataFromConsole.get("labelPost"));
        label.setPostId(post.getId());

        JDBCLabelRepositoryImpl jdbcLabelRepository = new JDBCLabelRepositoryImpl();
        jdbcLabelRepository.save(label);

        List<Label> labels = new ArrayList<>();
        labels.add(label);
        post.setLabels(labels);
        postService.create(post);

    }


    public void getPostById() {
        Integer writerId = postView.getIdFromConsole("Введите id поста");
        Post post = postService.getPostById(writerId);
        postView.getPostById(post);

    }

    public void getAllPosts() {
        postView.getAllPosts(postService.getAllPosts());
    }

    public void deleteWriterById() {
        Integer postId = postView.getIdFromConsole("Введите post id для удаления");
        postService.deletePostById(postId);
    }

    public void updatePostById() {
        Integer postId = postView.getIdFromConsole("Введите поста id для обновления");

        HashMap<String, String> updatedData = postView.updatePostById();

        Post post = postService.getPostById(postId);

        updatedData.forEach((key, value) -> {
            switch (key) {
                case "content":
                    post.setContent(updatedData.get("content"));
                    break;
                case "created":
                    post.setCreated(updatedData.get("content"));
                    break;
                case "updated":
                    post.setUpdated(updatedData.get("updated"));
                    break;
                case "writerId":
                    post.setWriterId(Integer.parseInt(updatedData.get("writerId")));
                    break;
                case "status":
                    post.setPostStatus(PostStatus.valueOf(updatedData.get("status")));
                    break;
            }
        });

        postService.updatePost(post);
    }
}


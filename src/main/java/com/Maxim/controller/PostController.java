package com.Maxim.controller;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;
import com.Maxim.repository.jdbc.JDBCLabelRepositoryImpl;
import com.Maxim.repository.jdbc.JDBCPostLabelRepository;
import com.Maxim.service.PostService;
import com.Maxim.service.WriterService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostController {

    private  PostService postService = new PostService();

    public void savePost(HashMap<String, String> dataFromConsole) {
        Post post = new Post();

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

        post.setContent(dataFromConsole.get("postText"));
        post.setPostStatus(PostStatus.UNDER_REVIEW);
//        post.setWriterId(writer.getId());


        Label label = new Label();
        label.setName(dataFromConsole.get("labelPost"));
        JDBCLabelRepositoryImpl jdbcLabelRepository = new JDBCLabelRepositoryImpl();
//        jdbcLabelRepository.save(label);

        JDBCPostLabelRepository jdbcPostLabelRepository = new JDBCPostLabelRepository();
        jdbcPostLabelRepository.save(post,label);

        List<Label> labels = new ArrayList<>();
        labels.add(label);
        post.setLabels(labels);
        postService.savePost(post);

    }


    public Post getPostById(Integer postId) {
        return postService.getPostById(postId);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public void deletePostById(Integer postId) {
        postService.deletePostById(postId);
    }

    public Post updatePostById(Integer postId,HashMap<String, String> updatedData) {

        Post post = postService.getPostById(postId);

        updatedData.forEach((key, value) -> {
            switch (key) {
                case "content":
                    post.setContent(updatedData.get("content"));
                    break;
                case "created":
                    post.setCreated(updatedData.get("created"));
                    break;
                case "updated":
                    post.setUpdated(updatedData.get("updated"));
                    break;
                case "writerId":
//                    post.setWriterId(Integer.parseInt(updatedData.get("writerId")));
                    break;
                case "status":
                    post.setPostStatus(PostStatus.valueOf(updatedData.get("status")));
                    break;
            }
        });

       return postService.updatePostById(post);
    }
}


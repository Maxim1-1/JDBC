package com.Maxim.controller;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;
import com.Maxim.service.LabelService;
import com.Maxim.service.PostService;
import com.Maxim.service.WriterService;


import java.util.HashMap;
import java.util.List;

public class PostController {

    private PostService postService = new PostService();

    public void savePost(HashMap<String, String> dataFromConsole) {
        Post post = new Post();
        WriterService writerService = new WriterService();
        LabelService labelService = new LabelService();

        Writer writer;

        if (dataFromConsole.get("writerId") != null) {
            writer = writerService.getWriterById(Integer.valueOf(dataFromConsole.get("writerId")));
        } else {
            writer = new Writer();
            writer.setFirstName(dataFromConsole.get("firstName"));
            writer.setLastName(dataFromConsole.get("lastName"));
            writerService.save(writer);
        }
        post.setContent(dataFromConsole.get("postText"));
        post.setPostStatus(PostStatus.UNDER_REVIEW);
        post.setWriter(writer);


        if (dataFromConsole.get("labelPost") != null) {
            Label userLabel = labelService.getAllLabels().stream().filter(label1 -> label1.getName()==post.getLabels().get(0).getName()).findFirst().orElse(null);
            if (userLabel==null){
                Label newLabel = new Label();
                newLabel.setName(dataFromConsole.get("labelPost"));
                newLabel = labelService.saveLabel(newLabel);
                post.setLabel(newLabel);

            } else {
                post.setLabel(userLabel);
            }

        }
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

    public Post updatePostById(Integer postId, HashMap<String, String> updatedData) {

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


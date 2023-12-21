package com.Maxim.repository.jdbc.mappingUtils;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MappingUtils {
    public static void addPostAndLabelToWriter(ResultSet resultSet, Writer writer, Integer postId, Integer labelId) throws SQLException {

        if (postId != 0 & writer.getPosts().stream().anyMatch(post -> post.getId() == postId)) {
            Post post = writer.getPosts().stream()
                    .filter(post1 -> post1.getId() == postId)
                    .findFirst()
                    .orElse(null);
            Label label = new Label();
            label.setId(labelId);
            label.setName(resultSet.getString("name"));
            post.setLabel(label);

        } else if (postId != 0) {
            Post post = new Post();
            post.setId(postId);
            post.setContent(resultSet.getString("content"));
            post.setCreated(resultSet.getString("created"));
            post.setUpdated(resultSet.getString("updated"));
            post.setWriter(writer);
            post.setPostStatus(PostStatus.valueOf(resultSet.getString("status")));
            if (labelId != 0) {
                Label label = new Label();
                label.setId(labelId);
                label.setName(resultSet.getString("name"));
                post.setLabel(label);
            }
            writer.setPost(post);
        }
    }

    public static void addLabelToPost(ResultSet resultSet, Post post, Integer labelId) throws SQLException {
        if (labelId != 0 & post.getLabels().isEmpty()) {
            Label newLabel = new Label();
            newLabel.setId(labelId);
            newLabel.setName(resultSet.getString("name"));
            post.setLabel(newLabel);
        } else if (post.getLabels().stream().anyMatch(label -> label.getId() != labelId)) {
            Label newLabel = new Label();
            newLabel.setId(labelId);
            newLabel.setName(resultSet.getString("name"));
            post.setLabel(newLabel);
        }
    }
}


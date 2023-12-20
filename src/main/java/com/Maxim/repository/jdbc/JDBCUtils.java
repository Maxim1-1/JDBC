package com.Maxim.repository.jdbc;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCUtils {
    public static void convertPostAndLabelToModel(ResultSet resultSet,Writer writer, Integer postId, Integer labelId) throws SQLException {
        if (postId != 0 & writer.getPosts().stream().anyMatch(post -> post.getId() == postId)) {
            Post postWithNewLabel = writer.getPosts().stream()
                    .filter(post1 -> post1.getId() == postId)
                    .findFirst()
                    .orElse(null);
            Label label = new Label();
            label.setId(labelId);
            label.setName(resultSet.getString("name"));
            postWithNewLabel.setLabel(label);
            List<Post> newPosts = writer.getPosts().stream().map(post -> post.getId() == postId ? postWithNewLabel : post)
                    .collect(Collectors.toList());
            writer.setPosts(newPosts);

        } else if (postId != 0) {
            Post post = new Post();
            post.setId(postId);
            post.setContent(resultSet.getString("content"));
            post.setCreated(resultSet.getString("created"));
            post.setUpdated(resultSet.getString("updated"));
            post.setWriterId(writer);
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
}

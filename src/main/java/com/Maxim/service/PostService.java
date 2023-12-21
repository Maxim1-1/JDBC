package com.Maxim.service;


import com.Maxim.dbutils.CrudOperation;
import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.repository.jdbc.JDBCPostRepositoryImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PostService {

    private final JDBCPostRepositoryImpl jdbc = new JDBCPostRepositoryImpl();

    public Post savePost(Post post) {
       return jdbc.save(post);
    }

    public Post getPostById(Integer postId) {
        return jdbc.getById(postId);
    }

    public List<Post> getAllPosts() {
        return jdbc.getAll();
    }

    public Post updatePostById(Post post) {
        return jdbc.update(post);
    }

    public void deletePostById(Integer id) {
        jdbc.deleteById(id);
    }
}

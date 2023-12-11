package com.Maxim.service;


import com.Maxim.model.Post;
import com.Maxim.repository.jdbc.JDBCPostRepositoryImpl;


import java.util.List;

public class PostService {

    private static final JDBCPostRepositoryImpl jdbc = new JDBCPostRepositoryImpl();

    public void savePost(Post post) {
        jdbc.save(post);
    }

    public Post getPostById(Integer postId){
        return jdbc.getById(postId);
    }

    public List<Post> getAllPosts() {
        return jdbc.getAll();
    }

    public void updatePostById(Post post) {
        jdbc.update(post);
    }

    public void deletePostById (Integer id){
        jdbc.deleteById(id);
    }
}

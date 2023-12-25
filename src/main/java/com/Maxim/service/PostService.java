package com.Maxim.service;


import com.Maxim.model.Post;
import com.Maxim.repository.PostRepository;
import com.Maxim.repository.jdbc.JDBCPostRepositoryImpl;


import java.util.List;

public class PostService {

    private final PostRepository repository = new JDBCPostRepositoryImpl();

    public Post savePost(Post post) {
       return repository.save(post);
    }

    public Post getPostById(Integer postId) {
        return repository.getById(postId);
    }

    public List<Post> getAllPosts() {
        return repository.getAll();
    }

    public Post updatePostById(Post post) {
        return repository.update(post);
    }

    public void deletePostById(Integer id) {
        repository.deleteById(id);
    }
}

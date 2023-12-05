package com.Maxim.repository.jdbc;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.repository.PostRepository;
import com.Maxim.crud_data_base.base.Connector;
import com.Maxim.crud_data_base.crud_operation.CrudOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class JDBCPostRepositoryImpl implements PostRepository {

    private String tableName = "post";
    private CrudOperation crudOperation = new CrudOperation();

    @Override
    public Post getById(Integer id) {
        try {
            return getAll().stream()
                    .filter(label -> label.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Хранилище пусто");
        }
        return null;
    }

    @Override
    public List<Post> getAll() {

        List<Post> posts = new ArrayList<>();

        String sqlExpression = String.format("select * FROM %s", tableName);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                Integer id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                String created = resultSet.getString("created");
                String updated = resultSet.getString("updated");
                String status = resultSet.getString("status");
                Integer writerId = resultSet.getInt("writerId");

                JDBCPostLabelRepository jdbcPostLabelRepository = new JDBCPostLabelRepository();

                List<Label> labels = jdbcPostLabelRepository.getPostsById(id);

                Post post = new Post();

                post.setId(id);
                post.setLabels(labels);
                post.setContent(content);
                post.setCreated(created);
                post.setUpdated(updated);
                post.setPostStatus(PostStatus.valueOf(status));
                post.setWriterId(writerId);
                posts.add(post);
            }

        } catch (SQLException e) {
            System.out.print("Ошибка при выполнении запроса");
            throw new RuntimeException(e);
        }

        return posts;
    }

    @Override
    public void save(Post post) {
        HashMap<String,Object> newPostParams = new HashMap<>();

        newPostParams.put("id",post.getId());
        newPostParams.put("content",post.getContent());
        newPostParams.put("created",post.getCreated());
        newPostParams.put("updated",post.getUpdated());
        newPostParams.put("writerId",post.getWriterId());
        newPostParams.put("status",post.getPostStatus());

        crudOperation.insert(tableName,newPostParams);
        System.out.print("post успешно сохранен, "+"id = "+post.getId());
    }

    @Override
    public void update(Post updatePost) {
        HashMap<String,Object> updatePostParams = new HashMap<>();

        updatePostParams.put("id",updatePost.getId());
        updatePostParams.put("content",updatePost.getContent());
        updatePostParams.put("created",updatePost.getCreated());
        updatePostParams.put("updated",updatePost.getUpdated());
        updatePostParams.put("writerId",updatePost.getWriterId());
        updatePostParams.put("status",updatePost.getPostStatus());

        crudOperation.updateById(tableName,updatePostParams,updatePost.getId());
        System.out.print("post успешно обновлен");
    }

    @Override
    public void deleteById(Integer id) {

        Post deletePost = getAll().stream()
                .filter(label -> label.getId() == id)
                .findFirst()
                .orElse(null);

        deletePost.setPostStatus(PostStatus.DELETED);
        update(deletePost);
        System.out.print("post успешно удален");
    }
}

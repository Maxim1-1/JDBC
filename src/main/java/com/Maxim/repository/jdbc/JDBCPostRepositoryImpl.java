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
import java.util.List;
import java.util.stream.Collectors;

public class JDBCPostRepositoryImpl implements PostRepository {

    private String tableName = "task2_2.post";
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

                JDBCLabelRepositoryImpl jdbcLabelRepository = new JDBCLabelRepositoryImpl();

                List<Label> labels = jdbcLabelRepository.getAll();
                List<Label> sortedLabel = labels.stream().filter(label -> label.getPostId()==id).collect(Collectors.toList());

                Post post = new Post();

                post.setId(id);
                post.setLabels(sortedLabel);
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
        try {
            crudOperation.insert(tableName,"id,content, created,updated, writerId, status",
                    String.format("%s, '%s','%s','%s',%s,'%s'",post.getId(),post.getContent(),post.getCreated(),post.getUpdated(),post.getWriterId(),post.getPostStatus()));
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Хранилище пусто");
        }


    }

    @Override
    public void update(Post updatePost) {

        crudOperation.update(tableName,String.format("set content = '%s', created = '%s', updated = '%s', writerId = %s, status = '%s'",
                        updatePost.getContent(),updatePost.getCreated(),updatePost.getUpdated(),updatePost.getWriterId(), updatePost.getPostStatus()),
                String.format("where id = %s",updatePost.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        crudOperation.delete(tableName,String.format("where id = %s",id));
    }
}

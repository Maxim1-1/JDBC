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
import java.util.stream.Collectors;

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
        HashMap<String,Object> updatePostParams = new HashMap<>();

        updatePostParams.put("id",updatePost.getId());
        updatePostParams.put("id",updatePost.getContent());
        updatePostParams.put("id",updatePost.getUpdated());
        updatePostParams.put("id",updatePost.getCreated());
        updatePostParams.put("id",updatePost.getWriterId());
        updatePostParams.put("id",updatePost.getLabels());



//        crudOperation.updateById();



//        "content":"test"

//        "update ? set %s  where = ?"


//        1.  пройти по всей мапе -> content = ?,... // "update ? set content = ?, created = ?  where = ?"
//        2.  for value in hashmap:
//        counter = 1;
//                if value is int:
//                  statment.setInt(counter, value)
//                if value is int:
////                  statment.setString(counter, value)
//                 и для Enum
//
//
//
//
//
//
//        3.
//
//


// нужно идти по хэшмапе и


//        String parameters = String.format("set content = '%s', created = '%s', updated = '%s', writerId = %s, status = '%s'",
//                updatePost.getContent(),updatePost.getCreated(),updatePost.getUpdated(),updatePost.getWriterId(), updatePost.getPostStatus());
//        String condition = String.format("where id = %s",updatePost.getId());

//        crudOperation.update(tableName,parameters,condition);
    }

    @Override
    public void deleteById(Integer id) {
        crudOperation.delete(tableName,String.format("where id = %s",id));
    }
}

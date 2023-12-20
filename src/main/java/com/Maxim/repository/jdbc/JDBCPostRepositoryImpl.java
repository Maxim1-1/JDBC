package com.Maxim.repository.jdbc;

import com.Maxim.model.Label;
import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;
import com.Maxim.repository.PostRepository;
import com.Maxim.dbutils.Connector;
import com.Maxim.dbutils.CrudOperation;
import com.Maxim.repository.jdbc.mappingUtils.MappingUtils;

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
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> getAll() {
        try {
            ResultSet resultSet = crudOperation.selectRowQuery(
                    "SELECT *\n" +
                            "FROM post\n" +
                            "LEFT JOIN writer ON post.writerId = writer.id\n" +
                            "LEFT JOIN post_labels ON post.id = post_labels.postid\n" +
                            "LEFT JOIN label ON post_labels.labelid = label.id ;");
            return mapResultSetToPost(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post save(Post post) {
        HashMap<String, Object> newPostParams = new HashMap<>();

        newPostParams.put("id", post.getId());
        newPostParams.put("content", post.getContent());
        newPostParams.put("created", post.getCreated());
        newPostParams.put("updated", post.getUpdated());
        newPostParams.put("writerId", post.getWriterId());
        newPostParams.put("status", post.getPostStatus());

//        crudOperation.insert(tableName, newPostParams);
        System.out.print("post успешно сохранен, " + "id = " + post.getId());
        return post;
    }

    @Override
    public Post update(Post updatePost) {
        HashMap<String, Object> updatePostParams = new HashMap<>();

        updatePostParams.put("id", updatePost.getId());
        updatePostParams.put("content", updatePost.getContent());
        updatePostParams.put("created", updatePost.getCreated());
        updatePostParams.put("updated", updatePost.getUpdated());
        updatePostParams.put("writerId", updatePost.getWriterId());
        updatePostParams.put("status", updatePost.getPostStatus());

//        if (getAll().stream().anyMatch(post -> post.getId() == updatePost.getId())) {
//            try {
////                Post post = mapResultSetToPost(crudOperation.updateById(tableName, updatePostParams, updatePost.getId()));
//
////                return post;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            System.out.print("укзанного id нет в таблице");
//        }

        return updatePost;
    }

    @Override
    public void deleteById(Integer id) {

    }

    private List<Post> mapResultSetToPost(ResultSet resultSet) throws SQLException {
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            int postId = resultSet.getInt(1);
            int labelId = resultSet.getInt("labelId");

            Post post = posts.stream()
                    .filter(post1 -> post1.getId() == postId)
                    .findFirst()
                    .orElse(null);
            if (post == null) {
                post = new Post();
                post.setId(postId);
                post.setContent(resultSet.getString("content"));
                post.setCreated(resultSet.getString("created"));
                post.setUpdated(resultSet.getString("updated"));

                Writer writer = new Writer();
                writer.setId(resultSet.getInt("writerId"));
                writer.setFirstName(resultSet.getString("firstName"));
                writer.setLastName(resultSet.getString("lastName"));
                post.setWriterId(writer);

                post.setPostStatus(PostStatus.valueOf(resultSet.getString("status")));
                MappingUtils.addLabelToPost(resultSet,post,labelId);
                posts.add(post);
            }
            MappingUtils.addLabelToPost(resultSet,post,labelId);
            }
            return posts;
        }
    }

package com.Maxim.repository.jdbc;

import com.Maxim.model.Post;
import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;
import com.Maxim.repository.PostRepository;
import com.Maxim.dbutils.CrudOperation;
import com.Maxim.repository.jdbc.mappingUtils.MappingUtils;

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
            ResultSet resultSet = crudOperation.selectRowQuery(String.format("SELECT *\n" +
                    "FROM post\n" +
                    "LEFT JOIN writer ON post.writerId = writer.id\n" +
                    "LEFT JOIN post_labels ON post.id = post_labels.postid\n" +
                    "LEFT JOIN label ON post_labels.labelid = label.id where post.id = %d;", id)
            );
            return mapResultSetToPost(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        newPostParams.put("content", post.getContent());
        newPostParams.put("writerId", post.getWriter().getId());
        newPostParams.put("status", post.getPostStatus());
        try {
            ResultSet resultSet = crudOperation.insert(tableName, newPostParams);
            Integer generatePostId = null;
            if (resultSet.next()) {
                generatePostId = resultSet.getInt(1);
            }
            HashMap<String, Object> postLabelsTableParams = new HashMap<>();
            postLabelsTableParams.put("postId", generatePostId);
            postLabelsTableParams.put("labelId",post.getLabels().get(0).getId());
            crudOperation.insert("post_labels", postLabelsTableParams);
            return getById(generatePostId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post update(Post updatePost) {
        HashMap<String, Object> updatePostParams = new HashMap<>();

        updatePostParams.put("id", updatePost.getId());
        updatePostParams.put("content", updatePost.getContent());
        updatePostParams.put("created", updatePost.getCreated());
        updatePostParams.put("updated", updatePost.getUpdated());
        updatePostParams.put("writerId", updatePost.getWriter().getId());
        updatePostParams.put("status", updatePost.getPostStatus());

        if (updatePost.getContent() != null) {
            updatePostParams.put("content", updatePost.getContent());
        } else if (updatePost.getPostStatus() != null) {
            updatePostParams.put("status", updatePost.getPostStatus());
        }

        crudOperation.updateById(tableName, updatePostParams, updatePost.getId());
        System.out.print("post успешно обновлен\n");
        return updatePost;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            crudOperation.selectRowQuery(String.format("UPDATE %s SET `status` = 'DELETED' WHERE (id = %d);",tableName,id));
            System.out.print("post успешно удален");
        } catch (NullPointerException exception) {
            System.out.print("укзанного id нет в таблице");
            exception.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                post.setWriter(writer);

                post.setPostStatus(PostStatus.valueOf(resultSet.getString("status")));
                MappingUtils.addLabelToPost(resultSet, post, labelId);
                posts.add(post);
            }
            MappingUtils.addLabelToPost(resultSet, post, labelId);
        }
        return posts;
    }
}

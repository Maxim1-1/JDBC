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

        CrudOperation crudOperation = new CrudOperation();
        try {

            ResultSet resultSet = crudOperation.selectAll("label", "post_labels", "id", "labelId");


            while (resultSet.next()) {
                String nameLabel = resultSet.getString("name");

                if (post.getLabels().stream().anyMatch(label -> label.getName().equalsIgnoreCase(nameLabel))) {
                    Integer labelId = resultSet.getInt("id");
                    Label findLabel = post.getLabels().stream().filter(label1 -> label1.getName().equalsIgnoreCase(nameLabel)).findFirst().orElse(null);
                    findLabel.setId(labelId);

                    if (resultSet.getString("postId")==null & resultSet.getString("labelId")==null) {
                        HashMap<String,Object> params =  new HashMap<>();
                        params.put("postId",post.getId());
                        params.put("labelId", labelId);
                        crudOperation.insert("post_labels",params);
                    }
                } else {
                    LabelService labelService = new LabelService();
                    Label newLabel = post.getLabels().get(0);
                    Integer generatedId = labelService.saveLabel(newLabel).getId();
                    newLabel.setId(generatedId);
                    jdbc.save(post);
                    HashMap<String,Object> params =  new HashMap<>();
                    params.put("postId",post.getId());
                    params.put("labelId", generatedId);
                    crudOperation.insert("post_labels",params);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

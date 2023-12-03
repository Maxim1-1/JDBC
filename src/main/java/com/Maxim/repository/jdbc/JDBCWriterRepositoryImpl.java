package com.Maxim.repository.jdbc;

import com.Maxim.model.Post;
import com.Maxim.model.Writer;
import com.Maxim.crud_data_base.base.Connector;
import com.Maxim.repository.WriterRepository;
import com.Maxim.crud_data_base.crud_operation.CrudOperation;
import com.Maxim.service.PostService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCWriterRepositoryImpl implements WriterRepository {

    private String tableName = "task2_2.writer";
    private CrudOperation crudOperation = new CrudOperation();

    @Override
    public Writer getById(Integer writerId) {
        return getAll().stream().filter(writer -> writer.getId() == writerId).findFirst().orElse(null);

    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();

        String sqlExpression = String.format("select * FROM %s", tableName);

        try (Connection connector = Connector.getConnect();
             PreparedStatement statement = connector.prepareStatement(sqlExpression);
             ) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                PostService postService = new PostService();
                List<Post> posts = postService.getAllPosts().stream().filter(post -> post.getWriterId()==id).collect(Collectors.toList());

                Writer writer = new Writer();
                writer.setLastName(lastName);
                writer.setFirstName(firstName);
                writer.setId(id);
                writer.setPost(posts);

                writers.add(writer);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return writers;
    }

    @Override
    public void save(Writer writer) {
        crudOperation.insert(tableName,"id,firstName,lastName",String.format("%s, '%s', '%s'",
                writer.getId(), writer.getFirstName(), writer.getLastName()));

    }

    @Override
    public void update(Writer updateWriter) {
        crudOperation.update(tableName,String.format("set firstName = '%s', lastName = '%s'", updateWriter.getFirstName(),updateWriter.getLastName()),
                String.format("where id = %s",updateWriter.getId()));

    }

    @Override
    public void deleteById(Integer writerId) {
        crudOperation.delete(tableName,String.format("where id = %s",writerId));
    }
}

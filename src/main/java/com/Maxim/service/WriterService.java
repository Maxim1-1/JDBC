package com.Maxim.service;

import com.Maxim.model.Post;
import com.Maxim.model.Writer;
import com.Maxim.repository.jdbc.JDBCWriterRepositoryImpl;

import java.util.List;

public class WriterService {

    private static final JDBCWriterRepositoryImpl jdbc = new JDBCWriterRepositoryImpl();

    public void save(Writer writer) {
        jdbc.save(writer);
    }

    public Writer getWriterById(Integer writerId){
        return jdbc.getById(writerId);
    }

    public List<Writer> getAllWriters() {
        return jdbc.getAll();
    }

    public void updateWriter(Writer writer) {
        jdbc.update(writer);
    }

    public void deleteWriterById (Integer id){
        jdbc.deleteById(id);
    }
}

package com.Maxim.service;


import com.Maxim.model.Writer;
import com.Maxim.repository.jdbc.JDBCWriterRepositoryImpl;

import java.util.List;

public class WriterService {

    private final JDBCWriterRepositoryImpl jdbc = new JDBCWriterRepositoryImpl();

    public Writer save(Writer writer) {
        return jdbc.save(writer);
    }

    public Writer getWriterById(Integer writerId){
        return jdbc.getById(writerId);
    }

    public List<Writer> getAllWriters() {
        return jdbc.getAll();
    }

    public Writer updateWriter(Writer writer) {
        return jdbc.update(writer);
    }

    public void deleteWriterById (Integer id){
        jdbc.deleteById(id);
    }
}

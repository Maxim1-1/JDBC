package com.Maxim.service;


import com.Maxim.model.Writer;
import com.Maxim.repository.WriterRepository;
import com.Maxim.repository.jdbc.JDBCWriterRepositoryImpl;

import java.util.List;

public class WriterService {

    private final WriterRepository repository = new JDBCWriterRepositoryImpl();

    public Writer save(Writer writer) {
        return repository.save(writer);
    }

    public Writer getWriterById(Integer writerId){
        return repository.getById(writerId);
    }

    public List<Writer> getAllWriters() {
        return repository.getAll();
    }

    public Writer updateWriter(Writer writer) {
        return repository.update(writer);
    }

    public void deleteWriterById (Integer id){
        repository.deleteById(id);
    }
}

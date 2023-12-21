package com.Maxim.controller;

import com.Maxim.model.Writer;
import com.Maxim.service.WriterService;
import com.Maxim.view.WriterView;

import java.util.HashMap;
import java.util.List;

public class WriterController {

    private WriterService writerService = new WriterService();

    public void save(HashMap<String,String> dataFromConsole ) {
        Writer writer = new Writer();
        writer.setFirstName(dataFromConsole.get("firstName"));
        writer.setLastName(dataFromConsole.get("lastName"));
        writerService.save(writer);
    }


    public Writer getWriterById(Integer writerId) {
        return writerService.getWriterById(writerId);

    }

    public List<Writer> getAllWriter() {
       return writerService.getAllWriters();
    }

    public void deleteWriterById(Integer writerId) {
        writerService.deleteWriterById(writerId);
    }

    public void updateWriterById(Integer writerId,HashMap<String, String> updatedData ) {
        Writer writer = new Writer();
        writer.setId(writerId);

        updatedData.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    writer.setFirstName(updatedData.get("firstName"));
                    break;
                case "lastName":
                    writer.setLastName(updatedData.get("lastName"));
                    break;
            }
        });
        writerService.updateWriter(writer);
    }
}

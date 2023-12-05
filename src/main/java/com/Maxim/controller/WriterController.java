package com.Maxim.controller;

import com.Maxim.model.PostStatus;
import com.Maxim.model.Writer;
import com.Maxim.service.WriterService;
import com.Maxim.view.WriterView;

import java.util.HashMap;

public class WriterController {

    private static final WriterService writerService = new WriterService();
    private WriterView writerView = new WriterView();


    public void save() {
        Writer writer = new Writer();

        HashMap<String, String> dataFromConsole = writerView.create();
        writer.setFirstName(dataFromConsole.get("firstName"));
        writer.setLastName(dataFromConsole.get("lastName"));
        writerService.save(writer);
    }


    public void getWriterById() {
        Integer writerId = writerView.getIdFromConsole("Введите id писателя");
        Writer writer = writerService.getWriterById(writerId);
        writerView.getWriterById(writer);
    }

    public void getAllWriter() {
        writerView.getAllWriters(writerService.getAllWriters());
    }

    public void deleteWriterById() {
        Integer writerId = writerView.getIdFromConsole("Введите writer id для удаления");
        writerService.deleteWriterById(writerId);
    }

    public void updateWriterById() {
        Integer writerId = writerView.getIdFromConsole("Введите поста id для обновления");

        HashMap<String, String> updatedData = writerView.updateWriterById();
        Writer writer = writerService.getWriterById(writerId);

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

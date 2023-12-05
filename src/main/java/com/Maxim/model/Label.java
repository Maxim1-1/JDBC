package com.Maxim.model;

import java.util.concurrent.ThreadLocalRandom;

public class Label {
    private int id;
    private String name;

    public Label() {
        this.id = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE)+1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

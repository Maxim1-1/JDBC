package com.Maxim.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Writer {
    private int id;
    private String firstName;
    private String lastName;
    private List<Post> post;


    public Writer() {
        this.id = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }


}

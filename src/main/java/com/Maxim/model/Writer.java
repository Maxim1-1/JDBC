package com.Maxim.model;

import java.util.ArrayList;
import java.util.List;

public class Writer {
    private int id;
    private String firstName;
    private String lastName;
    private List<Post> post = new ArrayList<>();


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

    public List<Post> getPosts() {
        return post;
    }

    public void setPosts(List<Post> post) {
        this.post = post;
    }
    public void setPost(Post post) {
        this.post.add(post);
    }


}

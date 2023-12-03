package com.Maxim.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Writer {

    @Override
    public String toString() {
        return "Writer{"
                +
                "id="+id+
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", post=" + post +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

//    private static int id = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);;
    private  int id ;
    private String firstName;
    private String lastName;
    private List<Post> post;
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

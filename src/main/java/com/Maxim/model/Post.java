package com.Maxim.model;

import java.util.List;

public class Post {
    private int id;
    private String content;
    private String created;
    private String updated;
    private PostStatus postStatus;
    private List<Label> labels;

    private int writerId;

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", postStatus=" + postStatus +
                ", labels=" + labels +
                ", writerId=" + writerId +
                '}';
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }




}

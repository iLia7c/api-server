package com.networkedassets.api.entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class Post {

    private Long userId;
    private Long id;
    private String title;
    private String body;

    private List<Comment> commentList;

    public Post() {
        commentList = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void updateComments(List<Comment> list) {
        commentList.addAll(list);
    }

    public void cleanComments() {
        commentList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(userId, post.userId)
                && Objects.equals(id, post.id)
                && Objects.equals(title, post.title)
                && Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }
}

package com.networkedassets.api.services;

import com.networkedassets.api.entities.Post;
import com.networkedassets.api.entities.Comment;

import java.util.List;

public interface PostService {
    /**
     * Get posts from remote server
     * @return list of posts
     */
    List<Post> getPosts();

    /**
     * Get all comments for a given post
     * @return list of comments
     */
    List<Comment> getComments(long postId);
}

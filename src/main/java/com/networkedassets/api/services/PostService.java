package com.networkedassets.api.services;

import com.networkedassets.api.entities.Post;

import java.util.List;

public interface PostService {
    /**
     * Get posts from remote server
     * @return list of posts
     */
    List<Post> getPosts();
}

package com.networkedassets.api.controllers;

import com.networkedassets.api.entities.Comment;
import com.networkedassets.api.entities.Post;
import com.networkedassets.api.services.PostService;
import com.networkedassets.api.utils.SaveUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Posts")
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // TODO separate tests for controllers
    @Operation(summary = "Save posts", description = "Saves all posts to provided path")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/savePosts")
    public void saveAll(@RequestBody String path) {
        // TODO move to another place
        List<Post> posts = postService.getPosts();

        for (Post post : posts) {
            List<Comment> comments = postService.getComments(post.getId());
            post.appendComments(comments);
        }
        // end TODO

        SaveUtils.save(path, posts);
    }
}

package com.networkedassets.api.controllers;

import com.networkedassets.api.services.PostService;
import com.networkedassets.api.utils.SaveUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Save posts", description = "Saves all posts to provided path")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/savePosts")
    public void saveAll(@RequestBody String path) {
        SaveUtils.save(path, postService.getPosts());
    }
}

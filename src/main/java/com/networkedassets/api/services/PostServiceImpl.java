package com.networkedassets.api.services;

import com.networkedassets.api.entities.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Value("${remote.posts}")
    private String postsUri;

    @Override
    public List<Post> getPosts(){
        Flux<Post> UserFlux = WebClient.create()
                .get()
                .uri(postsUri)
                .retrieve()
                .bodyToFlux(Post.class);

        return UserFlux.collectList().block();
    }

    public String getPostsUri() {
        return postsUri;
    }

    public void setPostsUri(String postsUri) {
        this.postsUri = postsUri;
    }
}

package com.networkedassets.api.cucumber;

import com.networkedassets.api.entities.Post;
import com.networkedassets.api.entities.Comment;
import com.networkedassets.api.services.PostService;
import com.networkedassets.api.services.PostServiceImpl;
import com.networkedassets.api.utils.SaveUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.After;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CucumberContextConfiguration
public class StepDefinition {

    private String remoteUrl;
    private String path;
    private PostService postService;
    private List<Post> posts;

    private void initializePostService() {
        if (postService == null) {
            postService = new PostServiceImpl();
            ((PostServiceImpl)postService).setPostsUri(remoteUrl);
        }
    }

    @Given("remote server url {string}")
    public void posts_remote_server_url(String url) {
        remoteUrl = url;
    }

    @When("client fetches all posts from remote server")
    public void client_fetches_all_posts_from_remote_server() {
        initializePostService();

        posts = postService.getPosts();

        for (Post postIndex : posts) {
            List<Comment> comments = postService.getComments(postIndex.getId());
            postIndex.updateComments(comments);
        }
    }

    @Then("all posts are stored in {string} with each post in a separate file with name post_id.json")
    public void all_posts_are_stored_in_with_each_post_in_a_separate_file_with_name_post_id_json(String path) throws IOException {
        this.path = path;
        SaveUtils.save(path, posts);

        for (Post postIndex : posts) {
            String fileName = postIndex.getId() + ".json";
            Path postIndexPath = Paths.get(path, fileName);
            Assertions.assertTrue(Files.exists(postIndexPath));
        }
    }

    @Then("all posts are stored with its comments")
    public void all_posts_are_stored_with_its_comments() {
        boolean atLeastOnePostWithComments = false;

        for (Post postIndex : posts) {
            atLeastOnePostWithComments |= !postIndex.getCommentList().isEmpty();
            if (atLeastOnePostWithComments) {
                break;
            }
        }

        Assertions.assertTrue(atLeastOnePostWithComments);
    }

    @After
    public void removePostsIfPresent() throws IOException {
        for (Post postIndex : posts) {
            String fileName = postIndex.getId() + ".json";
            Path postIndexPath = Paths.get(path, fileName);
            if (Files.exists(postIndexPath)) {
                Files.delete(postIndexPath);
            }
        }
    }

}

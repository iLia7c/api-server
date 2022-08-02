package com.networkedassets.api.unit;

import com.networkedassets.api.entities.Comment;
import com.networkedassets.api.entities.Post;
import com.networkedassets.api.ex.StoreException;
import com.networkedassets.api.utils.SaveUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaveUtilsTest {

    @Test
    void savePostsPositive() throws IOException {
        Post post = getDefaultPost();

        String dir = "temp";
        Path dirPath = Paths.get(dir);
        Files.createDirectory(dirPath);

        SaveUtils.save(dir, Arrays.asList(post));
        Path pathToPost = Paths.get(dir, post.getId() + ".json");

        // check that file exists
        Assertions.assertTrue(Files.exists(pathToPost));

        // clean mess
        Files.delete(pathToPost);
        Files.delete(dirPath);
    }

    @Test
    void savePostsNegativeNoPath() throws IOException {
        assertThrows(StoreException.class,
                () -> SaveUtils.save(null, null));
    }

    private Post getDefaultPost() {
        Post post = new Post();
        post.setBody("body");
        post.setId(1L);
        post.setUserId(2L);
        post.setTitle("title");
        post.appendComments(Arrays.asList(new Comment()));
        return post;
    }
}

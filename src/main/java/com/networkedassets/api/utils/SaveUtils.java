package com.networkedassets.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networkedassets.api.entities.Post;
import com.networkedassets.api.ex.StoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaveUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveUtils.class);

    private SaveUtils() {
        throw new UnsupportedOperationException("SaveUtils is a utility class. It should not be instantiated.");
    }

    /**
     * save posts on local drive
     *
     * @param path  path to folder to save posts
     * @param posts lists of posts
     * @throws StoreException if some posts were not saved
     */
    public static void save(String path, List<Post> posts) throws StoreException {
        if (!Objects.nonNull(path)) {
            throw new StoreException("No path to store");
        }
        if (!Objects.nonNull(posts)) {
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> failedRecordings = new ArrayList<>();

        for (Post post : posts) {
            String fileName = String.format("%d.json", post.getId());
            Path filePath = Paths.get(path, fileName);
            try {
                String json = objectMapper.writeValueAsString(post);
                Files.write(filePath, json.getBytes());
                LOGGER.info(String.format("Recorded %s", fileName));
            } catch (IOException ex) {
                String message = String.format("Failed to record %s", fileName);
                LOGGER.error(message, ex);
                failedRecordings.add(message);
            }
        }

        if (failedRecordings.size() > 0) {
            throw new StoreException(String.join("\n", failedRecordings));
        }
    }
}

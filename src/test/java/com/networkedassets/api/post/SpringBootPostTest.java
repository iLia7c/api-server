package com.networkedassets.api.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SpringBootPostTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostsPositive() throws Exception {
        mockMvc.perform(post("/posts/savePosts", "H:\\Source\\posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("H:\\Source\\posts"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testPostsNegative() throws Exception {
        mockMvc.perform(post("/posts/savePosts", "H:\\Source\\posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Wrong path"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}

package com.julianmartinez.sportscentrerest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.julianmartinez.sportscentrerest.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ResourceController.class)
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllResources() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/resources")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void shouldGetResourceById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/resources/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void shouldPostNewResource() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/resources")
                        .content(this.asJsonString(Resource.builder().id(1).name("Tennis 1").build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tennis 1"));
    }

    @Test
    void shouldPutResourceById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/resources/{id}", 1)
                        .content(this.asJsonString(Resource.builder().name("Tennis A").build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tennis A"));
    }

    @Test
    void shouldDeleteResourceById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/resources/{id}", 1))
                .andExpect(status().isNoContent());
    }

    public String asJsonString(final Resource object) {
        try {
            return new ObjectMapper()
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .writeValueAsString(object);
        } catch (final RuntimeException | JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

}
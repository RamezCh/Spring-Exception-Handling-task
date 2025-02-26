package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAnimalSpecies_returnIllegalArgumentException() throws Exception {
        // WHEN & THEN
        mvc.perform(get("/api/animals/cat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                {
                    "message": "Only 'dog' is allowed"
                }
                """));
    }

    @Test
    void getAllAnimals_returnNoSuchElementException() throws Exception {
        // WHEN & THEN
        mvc.perform(get("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                {
                    "message": "No Animals found"
                }
                """));
    }
}
package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getCarBrand_returnIllegalArgumentException() throws Exception {
        // WHEN & THEN
        mvc.perform(get("/api/cars/bmw")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                // We didn't test timestamp because its complicated getting same time
                .andExpect(content().json("""
                {
                    "message": "Only 'porsche' allowed"
                }
                """));
    }

    @Test
    void getAllCars_returnNoSuchElementFound() throws Exception {
        // WHEN
        mvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                {
                    "message": "No Cars found"
                }
                """
                ));
    }
}
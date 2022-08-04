package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityClearanceControllerTest {

    @MockBean
    SecurityClearanceRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/securityclearance")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();

        SecurityClearance securityClearance = new SecurityClearance(0, null);
        String securityClearanceJson = jsonMapper.writeValueAsString(securityClearance);

        var request = post("/api/securityclearance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(securityClearanceJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn201WhenValid() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();

        SecurityClearance securityClearance = new SecurityClearance(0, "Classified");
        SecurityClearance expected = new SecurityClearance(3, "Classified");

        when(repository.add(any())).thenReturn(expected);

        String securityClearanceJson = jsonMapper.writeValueAsString(securityClearance);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/securityclearance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(securityClearanceJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));

    }


}
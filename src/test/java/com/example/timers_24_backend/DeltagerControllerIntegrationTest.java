package com.example.timers_24_backend;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeltagerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @BeforeEach
    public void setUp() {
        deltagerRepository.deleteAll();
    }

    @Test
    public void testGetAllDeltager() throws Exception {
        mockMvc.perform(get("/api/deltager"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    public void testCreateDeltager() throws Exception {
        DeltagerDto deltagerDto = new DeltagerDto(UUID.randomUUID(), "Test Name", "Mand", 25, "Test By");

        mockMvc.perform(post("/api/deltager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltagerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.navn").value("Test Name"))
                .andExpect(jsonPath("$.kon").value("Mand"))
                .andExpect(jsonPath("$.alder").value(25))
                .andExpect(jsonPath("$.klub").value("Test By"));
    }

    @Test
    public void testUpdateDeltager() throws Exception {
        Deltager deltager = new Deltager();
        deltager.setId(UUID.randomUUID());
        deltager.setNavn("Original Name");
        deltager.setKon("Mand");
        deltager.setAlder(25);
        deltager.setKlub("Original By");

        Deltager savedDeltager = deltagerRepository.save(deltager);

        DeltagerDto updatedDeltagerDto = new DeltagerDto(savedDeltager.getId(), "Updated Name", "Kvinde", 30, "Updated By");

        mockMvc.perform(put("/api/deltager/" + savedDeltager.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDeltagerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.navn").value("Updated Name"))
                .andExpect(jsonPath("$.kon").value("Kvinde"))
                .andExpect(jsonPath("$.alder").value(30))
                .andExpect(jsonPath("$.klub").value("Updated By"));
    }

    @Test
    public void testDeleteDeltager() throws Exception {
        Deltager deltager = new Deltager();
        deltager.setId(UUID.randomUUID());
        deltager.setNavn("Test Name");
        deltager.setKon("Mand");
        deltager.setAlder(25);
        deltager.setKlub("Test By");

        Deltager savedDeltager = deltagerRepository.save(deltager);

        mockMvc.perform(delete("/api/deltager/" + savedDeltager.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Deltager slettet"));
    }
}

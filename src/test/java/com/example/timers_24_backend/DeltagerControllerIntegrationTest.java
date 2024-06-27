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

    // Autowire MockMvc, ObjectMapper og DeltagerRepository for at kunne udføre tests
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeltagerRepository deltagerRepository;

    // SetUp metode, der sletter alle eksisterende deltagere før hver test
    @BeforeEach
    public void setUp() {
        deltagerRepository.deleteAll();
    }

    // Test for at hente alle deltagere, som forventes at være tom ved første kald
    @Test
    public void testGetAllDeltager() throws Exception {
        mockMvc.perform(get("/api/deltager"))
                .andExpect(status().isOk())  // Forventet HTTP status 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Forventet content type JSON
                .andExpect(jsonPath("$.size()").value(0));  // Forventet tom liste
    }

    // Test for at oprette en ny deltager
    @Test
    public void testCreateDeltager() throws Exception {
        DeltagerDto deltagerDto = new DeltagerDto(UUID.randomUUID(), "Test Name", "Mand", 25, "Test By");

        mockMvc.perform(post("/api/deltager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltagerDto)))
                .andExpect(status().isCreated())  // Forventet HTTP status 201 Created
                .andExpect(jsonPath("$.navn").value("Test Name"))  // Forventet navn
                .andExpect(jsonPath("$.kon").value("Mand"))  // Forventet køn
                .andExpect(jsonPath("$.alder").value(25))  // Forventet alder
                .andExpect(jsonPath("$.klub").value("Test By"));  // Forventet klub
    }

    // Test for at opdatere en eksisterende deltager
    @Test
    public void testUpdateDeltager() throws Exception {
        // Opret og gem en deltager i repositoryet
        Deltager deltager = new Deltager();
        deltager.setId(UUID.randomUUID());
        deltager.setNavn("Original Name");
        deltager.setKon("Mand");
        deltager.setAlder(25);
        deltager.setKlub("Original By");

        Deltager savedDeltager = deltagerRepository.save(deltager);

        // Opret en DeltagerDto med opdaterede data
        DeltagerDto updatedDeltagerDto = new DeltagerDto(savedDeltager.getId(), "Updated Name", "Kvinde", 30, "Updated By");

        // Udfør en PUT anmodning for at opdatere deltageren
        mockMvc.perform(put("/api/deltager/" + savedDeltager.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDeltagerDto)))
                .andExpect(status().isOk())  // Forventet HTTP status 200 OK
                .andExpect(jsonPath("$.navn").value("Updated Name"))  // Forventet opdateret navn
                .andExpect(jsonPath("$.kon").value("Kvinde"))  // Forventet opdateret køn
                .andExpect(jsonPath("$.alder").value(30))  // Forventet opdateret alder
                .andExpect(jsonPath("$.klub").value("Updated By"));  // Forventet opdateret klub
    }

    // Test for at slette en eksisterende deltager
    @Test
    public void testDeleteDeltager() throws Exception {
        // Opret og gem en deltager i repositoryet
        Deltager deltager = new Deltager();
        deltager.setId(UUID.randomUUID());
        deltager.setNavn("Test Name");
        deltager.setKon("Mand");
        deltager.setAlder(25);
        deltager.setKlub("Test By");

        Deltager savedDeltager = deltagerRepository.save(deltager);

        // Udfør en DELETE anmodning for at slette deltageren
        mockMvc.perform(delete("/api/deltager/" + savedDeltager.getId()))
                .andExpect(status().isOk())  // Forventet HTTP status 200 OK
                .andExpect(content().string("Deltager slettet"));  // Forventet svartekst
    }
}

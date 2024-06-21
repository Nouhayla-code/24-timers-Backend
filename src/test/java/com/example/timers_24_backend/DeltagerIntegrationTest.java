package com.example.timers_24_backend;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeltagerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @BeforeEach
    public void setUp() {
        // Clear repositories before each test to ensure clean state
        deltagerRepository.deleteAll();
        disciplinRepository.deleteAll();
    }

    @Test
    public void testRegisterDeltagerWithDiscipliner() throws Exception {
        // Opret discipliner
        Disciplin disciplin1 = new Disciplin(UUID.randomUUID(), "100-meterløb (atletik)", "Tid");
        Disciplin disciplin2 = new Disciplin(UUID.randomUUID(), "Højdespring (atletik)", "Meter");
        disciplinRepository.saveAll(Arrays.asList(disciplin1, disciplin2));

        // Opret deltager DTO med disciplin ID'er
        DeltagerDto deltagerDto = new DeltagerDto();
        deltagerDto.setNavn("Test Deltager");
        deltagerDto.setKon("Mand");
        deltagerDto.setAlder(25);
        deltagerDto.setKlub("Test Klub");
        deltagerDto.setDiscipliner(Arrays.asList(disciplin1.getId(), disciplin2.getId()));

        // Log the JSON representation of deltagerDto
        String jsonDeltagerDto = objectMapper.writeValueAsString(deltagerDto);
        System.out.println("Request JSON: " + jsonDeltagerDto);

        // Send POST request to create deltager with discipliner
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/deltager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDeltagerDto))
                .andExpect(status().isCreated())
                .andReturn();

        // Retrieve created deltager from repository
        List<Deltager> allDeltagere = deltagerRepository.findAll();
        assertEquals(1, allDeltagere.size());
        Deltager oprettetDeltager = allDeltagere.get(0);

        // Assertions
        assertEquals(deltagerDto.getNavn(), oprettetDeltager.getNavn());
        assertEquals(deltagerDto.getKon(), oprettetDeltager.getKon());
        assertEquals(deltagerDto.getAlder(), oprettetDeltager.getAlder());
        assertEquals(deltagerDto.getKlub(), oprettetDeltager.getKlub());

        // Verify discipliner associations
        List<Disciplin> deltagerensDiscipliner = oprettetDeltager.getDiscipliner();
        assertEquals(2, deltagerensDiscipliner.size());
        assertTrue(deltagerensDiscipliner.stream().anyMatch(d -> d.getId().equals(disciplin1.getId())));
        assertTrue(deltagerensDiscipliner.stream().anyMatch(d -> d.getId().equals(disciplin2.getId())));
    }
}

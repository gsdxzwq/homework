package com.zhaowq.homework.controller;

import com.zhaowq.homework.dao.IncidentRepository;
import com.zhaowq.homework.domain.Incident;
import com.zhaowq.homework.service.IncidentService;
import com.zhaowq.homework.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
public class IncidentControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private IncidentService incidentService;

    @BeforeEach
    public void setup() {
        Incident incident = new Incident();
        incident.setId(1L);
        incident.setTitle("Initial Test Incident");
        incident.setDescription("This is an initial test incident for setup");
        incidentRepository.save(incident);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateIncident() throws Exception {
        Incident incident = new Incident();
        incident.setId(3L);
        incident.setTitle("New Test Incident");
        incident.setDescription("This is a new test incident");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/incident")
                        .contentType("application/json")
                        .content(JsonUtils.convertObjectToJsonBytes(incident)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateIncidentSuccess() throws Exception {
        List<Incident> incidents = incidentRepository.findAll();
        if (!incidents.isEmpty()) {
            Incident updatedIncident = new Incident();
            Long id = incidents.get(0).getId();
            updatedIncident.setId(id);
            updatedIncident.setTitle("Updated Title");
            updatedIncident.setDescription("Updated Description");

            mockMvc.perform(MockMvcRequestBuilders.put("/api/incident/" + id)
                            .contentType("application/json")
                            .content(JsonUtils.convertObjectToJsonBytes(updatedIncident)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    @Test
    public void testUpdateIncidentFailure() throws Exception {
        long nonExistentIncidentId = 9999L;
        Incident updatedIncident = new Incident();
        updatedIncident.setId(nonExistentIncidentId);
        updatedIncident.setTitle("Updated Title");
        updatedIncident.setDescription("Updated Description");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/incident/" + nonExistentIncidentId)
                        .contentType("application/json")
                        .content(JsonUtils.convertObjectToJsonBytes(updatedIncident)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteIncidentSuccess() throws Exception {
        List<Incident> incidents = incidentRepository.findAll();
        if (!incidents.isEmpty()) {
            Long incidentId = incidents.get(0).getId();
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/incident/" + incidentId))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    @Test
    public void testDeleteIncidentFailure() throws Exception {
        long nonExistentIncidentId = 9999L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/incident/" + nonExistentIncidentId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // 测试获取所有事件（incident）列表
    @Test
    public void testGetIncidents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/incident"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

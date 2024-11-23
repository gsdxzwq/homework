package com.zhaowq.homework.controller;

import com.zhaowq.homework.domain.Incident;
import com.zhaowq.homework.service.IncidentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(IncidentController.class)
public class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    @Test
    public void testCreateIncident() throws Exception {
        Incident mockIncident = new Incident(1L, "事件1", "这是一个测试事件", "2024-11-23 15:20");
        when(incidentService.createIncident(any(Incident.class))).thenReturn(mockIncident);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/incident")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"事件1\",\"description\":\"这是一个测试事件\",\"createTime\":\"2024-11-23 15:20\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("事件1"));
    }

    @Test
    public void testListAllIncidents() throws Exception {
        Incident incident1 = new Incident(1L, "活动1", "描述1", "2024-11-23 15:20");
        Incident incident2 = new Incident(2L, "活动2", "描述2", "2024-11-23 15:22");
        when(incidentService.listAllIncidents()).thenReturn(List.of(incident1, incident2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/incident"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("活动1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("活动2"));
    }
}

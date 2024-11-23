package com.zhaowq.homework.service;

import com.zhaowq.homework.domain.Incident;

import java.util.List;

public interface IncidentService {

    Incident createIncident(Incident incident);

    void deleteIncident(Long id);

    Incident updateIncident(Incident incident);

    List<Incident> listAllIncidents();
}

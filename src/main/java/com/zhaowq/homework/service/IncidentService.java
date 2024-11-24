package com.zhaowq.homework.service;

import com.zhaowq.homework.domain.Incident;

import java.util.List;

public interface IncidentService {

    Incident createIncident(Incident incident);

    void deleteIncident(Long id);

    Incident updateIncident(Long id, Incident incident);

    List<Incident> listAllIncidents();
}

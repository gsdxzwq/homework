package com.zhaowq.homework.service.impl;

import com.zhaowq.homework.dao.IncidentRepository;
import com.zhaowq.homework.domain.Incident;
import com.zhaowq.homework.service.IncidentService;
import com.zhaowq.homework.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {
    private final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Incident createIncident(Incident incident) {
        if (incidentRepository.findById(incident.getId()).isPresent()) {
            throw new RuntimeException("The saved incident already exists.");
        }
        incident.setCreateTime(TimeUtils.getCurrentTime());
        return incidentRepository.save(incident);
    }

    @Override
    public void deleteIncident(Long id) {
        if (incidentRepository.findById(id).isEmpty()) {
            throw new RuntimeException("The deleted incident does not exist.");
        }
        incidentRepository.deleteById(id);
    }

    @Override
    public Incident updateIncident(Long id, Incident incident) {
        Optional<Incident> existingIncidentOptional = incidentRepository.findById(id);
        if (existingIncidentOptional.isEmpty()) {
            throw new RuntimeException("The incident to be updated does not exist");
        }
        return incidentRepository.save(incident);

    }

    @Override
    public List<Incident> listAllIncidents() {
        return incidentRepository.findAll();
    }
}

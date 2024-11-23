package com.zhaowq.homework.service.impl;

import com.zhaowq.homework.dao.IncidentRepository;
import com.zhaowq.homework.domain.Incident;
import com.zhaowq.homework.service.IncidentService;
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
        if (incidentRepository.findById(incident.getId()).isPresent()){
            throw new RuntimeException("保存的事件已存在");
        }
        return incidentRepository.save(incident);
    }

    @Override
    public void deleteIncident(Long id) {
        if (incidentRepository.findById(id).isEmpty()){
            throw new RuntimeException("删除的时间不存在");
        }
        incidentRepository.deleteById(id);
    }

    @Override
    public Incident updateIncident(Incident incident) {
        Optional<Incident> existingIncidentOptional = incidentRepository.findById(incident.getId());
        if (existingIncidentOptional.isPresent()) {
            return incidentRepository.save(incident);
        }
        throw new RuntimeException("要修改的事件不存在");
    }

    @Override
    public List<Incident> listAllIncidents() {
        return incidentRepository.findAll();
    }
}

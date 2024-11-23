package com.zhaowq.homework.dao;

import com.zhaowq.homework.domain.Incident;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IncidentRepository {
    /**
     * 由于数据保存在内存中，这里借助LinkedHashMap
     */
    private static Map<Long, Incident> incidentMap = new LinkedHashMap<>();
    public Incident save(Incident incident) {
        incidentMap.put(incident.getId(), incident);
        return incident;
    }

    public void deleteById(Long id) {
        incidentMap.remove(id);
    }

    public Optional<Incident> findById(Long id) {
        return Optional.ofNullable(incidentMap.get(id));
    }

    public List<Incident> findAll() {
        return new ArrayList<>(incidentMap.values());
    }
}

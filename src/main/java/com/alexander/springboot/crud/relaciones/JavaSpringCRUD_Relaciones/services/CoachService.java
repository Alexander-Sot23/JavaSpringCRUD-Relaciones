package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.CoachDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CoachService {
    Page<CoachDTO> findAll(Pageable pageable);
    CoachDTO findById(Long id);
    CoachDTO save(Coach coach);
    CoachDTO saveWithGym(Coach coach, Long gymId);
    Set<CoachDTO> saveAll(List<Coach> coaches);
    CoachDTO update(Long id, Coach coach);
    CoachDTO updateGymId(Long gymId, Long coachId);
    void delete(Long id);
}

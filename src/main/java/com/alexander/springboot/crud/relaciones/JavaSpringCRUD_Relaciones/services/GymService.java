package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.GymDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface GymService {
    Page<GymDTO> findAll(Pageable pageable);
    GymDTO findById(Long id);
    GymDTO save(Gym gym);
    Set<GymDTO> saveAll(List<Gym> gyms);
    GymDTO update(Long id, Gym gym);
    void delete(Long id);
}

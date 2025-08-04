package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.GymDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Gym;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions.GymNotFoundException;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GymServiceImpl implements GymService{

    @Autowired
    private GymRepository gymRepository;

    @Override
    public Page<GymDTO> findAll(Pageable pageable) {
        return gymRepository.findAll(pageable).map(GymDTO::new);
    }

    @Override
    public GymDTO findById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException(id));
        return new GymDTO(gym);
    }

    @Override
    public GymDTO save(Gym gym) {
        Gym savedGym = gymRepository.save(gym);
        return new GymDTO(savedGym);
    }

    @Override
    public Set<GymDTO> saveAll(List<Gym> gyms) {
        return gymRepository.saveAll(gyms).stream()
                .map(GymDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public GymDTO update(Long id, Gym gym) {
        Gym gymUpdate = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException(id));
        gymUpdate.setName(gym.getName());
        gymUpdate.setAddress(gym.getAddress());
        Gym updateGym = gymRepository.save(gymUpdate);
        return new GymDTO(updateGym);
    }

    @Override
    public void delete(Long id) {
        Gym gymDelete = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException(id));
        gymRepository.delete(gymDelete);
    }
}

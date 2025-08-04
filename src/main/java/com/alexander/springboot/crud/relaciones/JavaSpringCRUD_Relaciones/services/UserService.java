package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.UserDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {
    Page<UserDTO> findAll(Pageable pageable);
    UserDTO findById(Long id);
    UserDTO save(User user);
    UserDTO saveWithCoach(User user, Long coachId);
    Set<UserDTO> saveAll(List<User> users);
    UserDTO update(Long id, User user);
    UserDTO updateCoachId(Long coachId, Long userId);
    void delete(Long id);
}

package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym,Long> {
}

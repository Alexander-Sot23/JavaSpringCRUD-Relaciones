package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Long> {
}

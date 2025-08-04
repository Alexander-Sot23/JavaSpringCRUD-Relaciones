package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

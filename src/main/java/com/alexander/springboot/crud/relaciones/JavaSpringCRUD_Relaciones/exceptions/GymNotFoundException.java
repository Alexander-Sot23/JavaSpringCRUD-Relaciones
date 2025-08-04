package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions;

public class GymNotFoundException extends RuntimeException{
    public GymNotFoundException(Long id){
        super ("Gym con el ID " + id + " no fue encontrado.");
    }
}

package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions;

public class CoachNotFoundException extends RuntimeException{
    public CoachNotFoundException(Long id){
        super ("Coach con el ID " + id + " no fue encontrado.");
    }
}

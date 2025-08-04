package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super ("Usuario con el ID " + id + " no fue encontrado.");
    }
}

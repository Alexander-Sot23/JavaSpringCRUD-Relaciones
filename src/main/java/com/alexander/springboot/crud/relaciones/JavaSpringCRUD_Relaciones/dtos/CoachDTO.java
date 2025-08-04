package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Coach;
import lombok.Getter;

@Getter
public class CoachDTO {

    private Long id;
    private String name;
    private String lastName;
    private String mail;
    private String specialty;
    private GymDTO gym;

    public CoachDTO(Coach coach) {
        this.id = coach.getId();
        this.name = coach.getName();
        this.lastName = coach.getLastName();
        this.mail = coach.getMail();
        this.specialty = coach.getSpecialty();
        if(coach.getGym()!=null){
            this.gym = new GymDTO(coach.getGym());
        }
    }
}

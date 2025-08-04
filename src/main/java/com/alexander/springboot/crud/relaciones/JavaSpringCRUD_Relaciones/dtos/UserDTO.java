package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.User;
import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String mail;
    private CoachDTO coach;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.mail = user.getMail();
        if(user.getCoach()!=null){
            this.coach = new CoachDTO(user.getCoach());
        }
    }
}

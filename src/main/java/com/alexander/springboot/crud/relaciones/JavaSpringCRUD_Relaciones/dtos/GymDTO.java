package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Gym;
import lombok.Getter;

@Getter
public class GymDTO extends Gym {

    private Long id;
    private String name;
    private String address;

    public GymDTO(Gym gym) {
        this.id = gym.getId();
        this.name = gym.getName();
        this.address = gym.getAddress();
    }
}

package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2,max = 25)
    private String name;

    @NotBlank
    @Size(min = 2,max = 25)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 45)
    private String mail;

    @NotBlank
    @Size(min = 2,max = 25)
    private String specialty;

    @Transient
    private Long gymId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    @JsonIgnoreProperties("coaches")
    private Gym gym;

    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return id != null && id.equals(coach.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

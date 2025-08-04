package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.controllers;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.GymDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Gym;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services.GymService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping
    public ResponseEntity<Page<GymDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));
        return ResponseEntity.ok(gymService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(gymService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Gym gym){
        return ResponseEntity.status(HttpStatus.CREATED).body(gymService.save(gym));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> bulk(@Valid @RequestBody List<Gym> gyms){
        return ResponseEntity.status(HttpStatus.CREATED).body(gymService.saveAll(gyms));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Gym gym){
        return ResponseEntity.ok(gymService.update(id, gym));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        gymService.delete(id);
        return ResponseEntity.ok().build();
    }

}

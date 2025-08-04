package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.controllers;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.CoachDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Coach;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services.CoachService;
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
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping
    public ResponseEntity<Page<CoachDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));
        return ResponseEntity.ok(coachService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(coachService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Coach coach){
        return ResponseEntity.status(HttpStatus.CREATED).body(coachService.save(coach));
    }

    @PostMapping("/{gymId}")
    public ResponseEntity<?> saveWithGym(@Valid @RequestBody Coach coach,@PathVariable Long gymId){
        return ResponseEntity.status(HttpStatus.CREATED).body(coachService.saveWithGym(coach, gymId));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> bulk(@Valid @RequestBody List<Coach> coaches){
        return ResponseEntity.status(HttpStatus.CREATED).body(coachService.saveAll(coaches));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Coach coach){
        return ResponseEntity.ok(coachService.update(id, coach));
    }

    @PutMapping("/{coachId}/gym/{gymId}")
    public ResponseEntity<?> updateGymId(@PathVariable Long gymId,@PathVariable Long coachId){
        return ResponseEntity.ok(coachService.updateGymId(gymId, coachId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        coachService.delete(id);
        return ResponseEntity.ok().build();
    }
}

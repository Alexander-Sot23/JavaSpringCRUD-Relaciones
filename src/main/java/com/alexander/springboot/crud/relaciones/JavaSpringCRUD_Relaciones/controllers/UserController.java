package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.controllers;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.UserDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.User;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services.UserService;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findALl(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/{coachId}")
    public ResponseEntity<?> saveWithCoach(@Valid @RequestBody User user, @PathVariable Long coachId){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveWithCoach(user, coachId));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> bulk(@Valid @RequestBody List<User> users){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAll(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user){
        return ResponseEntity.ok(userService.update(id, user));
    }

    @PutMapping("/{userId}/coach/{coachId}")
    public ResponseEntity<?> updateCoachId(@PathVariable Long coachId, @PathVariable Long userId){
        return ResponseEntity.ok(userService.updateCoachId(coachId, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}

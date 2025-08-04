package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.CoachDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Coach;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Gym;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions.CoachNotFoundException;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions.GymNotFoundException;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories.CoachRepository;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoachServiceImpl implements CoachService{

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private GymRepository gymRepository;

    @Override
    public Page<CoachDTO> findAll(Pageable pageable) {
        return coachRepository.findAll(pageable).map(CoachDTO::new);
    }

    @Override
    public CoachDTO findById(Long id) {
         Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException(id));
         return new CoachDTO(coach);
    }

    @Override
    public CoachDTO save(Coach coach) {
        Coach saveCoach = coachRepository.save(coach);
        return new CoachDTO(saveCoach);
    }

    @Override
    public CoachDTO saveWithGym(Coach coach, Long gymId) {
        Gym gymSave = gymRepository.findById(gymId)
                .orElseThrow(() -> new GymNotFoundException(gymId));
        coach.setGym(gymSave);
        Coach saveCoach = coachRepository.save(coach);
        return new CoachDTO(saveCoach);
    }

    @Override
    public Set<CoachDTO> saveAll(List<Coach> coaches) {
        coaches.forEach(coach -> {
            if(coach.getGymId()!=null){
                Gym gym = gymRepository.findById(coach.getGymId())
                        .orElseThrow(() -> new GymNotFoundException(coach.getGymId()));
                coach.setGym(gym);
            }
        });
        return coachRepository.saveAll(coaches).stream()
                .map(CoachDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public CoachDTO update(Long id, Coach coach) {
        Coach coachUpdate = coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException(id));
        coachUpdate.setName(coach.getName());
        coachUpdate.setLastName(coach.getLastName());
        coachUpdate.setSpecialty(coach.getSpecialty());
        coachUpdate.setMail(coach.getMail());
        Coach updateCoach = coachRepository.save(coachUpdate);
        return new CoachDTO(updateCoach);
    }

    @Override
    public CoachDTO updateGymId(Long gymId, Long coachId) {
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new GymNotFoundException(gymId));
        Coach coachUpdate = coachRepository.findById(coachId)
                .orElseThrow(() -> new CoachNotFoundException(coachId));
        coachUpdate.setGym(gym);
        Coach updateCoach = coachRepository.save(coachUpdate);
        return new CoachDTO(updateCoach);
    }

    @Override
    public void delete(Long id) {
        Coach coachDelete = coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException(id));
        coachRepository.delete(coachDelete);
    }
}

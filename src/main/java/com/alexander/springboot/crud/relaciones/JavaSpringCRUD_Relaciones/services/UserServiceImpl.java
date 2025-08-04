package com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.services;

import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.dtos.UserDTO;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.Coach;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.entities.User;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions.CoachNotFoundException;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.exceptions.UserNotFoundException;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories.CoachRepository;
import com.alexander.springboot.crud.relaciones.JavaSpringCRUD_Relaciones.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return new UserDTO(user);
    }

    @Override
    public UserDTO save(User user) {
        User saveUser = userRepository.save(user);
        return new UserDTO(saveUser);
    }

    @Override
    public UserDTO saveWithCoach(User user, Long coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new CoachNotFoundException(coachId));
        user.setCoach(coach);
        User userSave = userRepository.save(user);
        return new UserDTO(userSave);
    }

    @Override
    public Set<UserDTO> saveAll(List<User> users) {
        users.forEach(user -> {
            if(user.getCoachId()!=null){
                Coach coach = coachRepository.findById(user.getCoachId())
                        .orElseThrow(() -> new CoachNotFoundException(user.getCoachId()));
                user.setCoach(coach);
            }
        });
        return userRepository.saveAll(users).stream()
                .map(UserDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDTO update(Long id, User user) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userUpdate.setName(user.getName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setMail(user.getMail());
        User updateUser = userRepository.save(userUpdate);
        return new UserDTO(updateUser);
    }

    @Override
    public UserDTO updateCoachId(Long coachId, Long userId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new CoachNotFoundException(coachId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setCoach(coach);
        User updateUser = userRepository.save(user);
        return new UserDTO(updateUser);
    }

    @Override
    public void delete(Long id) {
        User userD  = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(userD);
    }
}

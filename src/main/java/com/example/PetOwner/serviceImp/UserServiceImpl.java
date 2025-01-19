package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.UserDTO;
import com.example.PetOwner.model.User;
import com.example.PetOwner.repositories.UserRepositories;
import com.example.PetOwner.service.UserService;
import com.example.PetOwner.utils.GeneralFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.InvalidMidiDataException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositories userRepositories;

    private final Random random = new Random();

    @Override
    public UserDTO addUser(User user) throws InvalidMidiDataException {

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDeletedFlag(false);
        User userRepo  = userRepositories.save(user);
        Long userId = GeneralFunction.generateId();
        userRepo.setUserId(userId);
        userRepositories.save(userRepo);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setName(userRepo.getName());
        userDTO.setCreatedAt(userRepo.getCreatedAt());

        return userDTO;
    }

    public User getUser(Long userId){
        User user =  userRepositories.findByUserId(userId).orElse(null);
        return user;
    }
}

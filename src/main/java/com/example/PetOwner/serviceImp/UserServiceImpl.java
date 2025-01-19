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


    @Override
    public UserDTO addUser(User user) throws InvalidMidiDataException {

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDeletedFlag(false);
        Long userId = GeneralFunction.generateId();
        user.setUserId(userId);
        User userRepo  = userRepositories.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setName(userRepo.getName());
        userDTO.setCreatedAt(userRepo.getCreatedAt());

        return userDTO;
    }

    public User getUser(Long userId){
        User user =  userRepositories.findByUserIdAndDeletedFlagFalse(userId);
        return user;
    }
}

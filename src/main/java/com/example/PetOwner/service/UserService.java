package com.example.PetOwner.service;

import com.example.PetOwner.dtos.UserDTO;
import com.example.PetOwner.model.User;

import javax.sound.midi.InvalidMidiDataException;

public interface UserService {

    public UserDTO addUser(User user) throws InvalidMidiDataException;

    public User getUser(Long id);
}

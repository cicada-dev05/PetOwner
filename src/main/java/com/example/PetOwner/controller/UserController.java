package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.UserDTO;
import com.example.PetOwner.model.User;
import com.example.PetOwner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.InvalidMidiDataException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) throws InvalidMidiDataException {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam("id") Long id){
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }
}

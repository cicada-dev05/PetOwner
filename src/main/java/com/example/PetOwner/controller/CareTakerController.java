package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.CareTakerDTO;
import com.example.PetOwner.service.CareTakerService;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/careTakers")
public class CareTakerController {

    @Autowired
    CareTakerService caretakerService;

    // Get all caretakers
    @GetMapping("/getAllCaretakers")
    public ResponseEntity<List<CareTakerDTO>> getAllCaretakers() {
        List<CareTakerDTO> caretakers = caretakerService.getAllCaretakers();
        return ResponseEntity.ok(caretakers);
    }

    // Get a caretaker by ID
    @GetMapping("/getCaretakerById")
    public ResponseEntity<CareTakerDTO> getCaretakerById(@RequestParam("caretakerId") Long caretakerId) {
        return new ResponseEntity<>(caretakerService.getCaretakerById(caretakerId),HttpStatus.OK);

    }

    // Create a new caretaker
    @PostMapping("/createCaretaker")
    public ResponseEntity<CareTakerDTO> createCaretaker(@RequestBody CareTakerDTO caretakerDTO) {
        CareTakerDTO createdCaretaker = caretakerService.createCaretaker(caretakerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCaretaker);
    }

    // Update a caretaker
    @PutMapping("/updateCaretaker")
    public ResponseEntity<CareTakerDTO> updateCaretaker(@RequestParam("/caretakerId") Long caretakerId,
                                                        @RequestBody CareTakerDTO caretakerDTO) {
        CareTakerDTO updatedCaretaker = caretakerService.updateCaretaker(caretakerId, caretakerDTO);
        return updatedCaretaker != null ? ResponseEntity.ok(updatedCaretaker) : ResponseEntity.notFound().build();
    }

    // Soft delete a caretaker
    @DeleteMapping("/deleteCaretaker")
    public ResponseEntity<Message> deleteCaretaker(@PathVariable("/caretakerId") Long caretakerId) {
        return new ResponseEntity<>(caretakerService.deleteCaretaker(caretakerId),HttpStatus.ACCEPTED);

    }
}

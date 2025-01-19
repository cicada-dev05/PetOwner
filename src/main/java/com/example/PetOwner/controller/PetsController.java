package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.PetsDTO;
import com.example.PetOwner.model.Pets;
import com.example.PetOwner.service.PetsService;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetsService petService;

    // Create or Update Pet
    @PostMapping("/addPet")
    public ResponseEntity<PetsDTO> createPet(@RequestBody Pets pet) {
        PetsDTO savedPet = petService.addPets(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PostMapping("/updatePet")
    public ResponseEntity<Message> updatePet(@RequestBody PetsDTO pet) {
        return new ResponseEntity<>(petService.updatePets(pet),HttpStatus.OK);
    }

    // Get Pet by ID
    @GetMapping("/getPet")
    public ResponseEntity<PetsDTO> getPetById(@RequestParam("petId") Long petId) {
        PetsDTO pet = petService.getPets(petId);
        return new ResponseEntity<>(pet,HttpStatus.OK);
    }

    // Get all Pets by OwnerId
    @GetMapping("/getPetByOwner")
    public ResponseEntity<List<PetsDTO>> getAllPets(@RequestParam("ownerId") Long ownerId) {
        List<PetsDTO> pets = petService.getAllPets(ownerId);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // Delete Pet by ID
    @DeleteMapping("/{petId}")
    public ResponseEntity<Message> deletePet(@PathVariable Long petId) {
        return new ResponseEntity<>(petService.deletePets(petId),HttpStatus.OK);

    }


}

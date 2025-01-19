package com.example.PetOwner.service;

import com.example.PetOwner.dtos.PetsDTO;
import com.example.PetOwner.model.Pets;
import com.example.PetOwner.utils.Message;

import java.util.List;

public interface PetsService {

    public PetsDTO addPets(Pets pets);

    public PetsDTO getPets(Long petId);

    public List<PetsDTO> getAllPets(Long ownerId);

    public Message updatePets(PetsDTO petsDTO);

    public Message deletePets(Long petId);
}

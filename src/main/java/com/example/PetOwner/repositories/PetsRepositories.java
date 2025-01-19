package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Pets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PetsRepositories extends MongoRepository<Pets,String> {

    Pets findByPetId(Long petId);

    @Query("{ 'ownerId' : ?0, 'deletedFlag' : false }")
    List<Pets> findByOwnerId(Long ownerId);
}

package com.example.PetOwner.repositories;

import com.example.PetOwner.model.PetService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PetServiceRepository extends MongoRepository<PetService,String> {

    PetService findByServiceId(Long serviceId);

    PetService findByServiceIdAndDeletedFlagFalse(Long serviceId);

    @Query("{ 'deletedFlag' : false }")
    List<PetService> findAllWithDeletedFlagFalse();
}

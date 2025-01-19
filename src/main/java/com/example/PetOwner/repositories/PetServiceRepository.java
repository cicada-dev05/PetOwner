package com.example.PetOwner.repositories;

import com.example.PetOwner.model.PetService;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetServiceRepository extends MongoRepository<PetService,String> {

    PetService findByServiceId(Long serviceId);
}

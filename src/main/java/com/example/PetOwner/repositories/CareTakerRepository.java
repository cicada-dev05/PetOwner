package com.example.PetOwner.repositories;

import com.example.PetOwner.model.CareTaker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CareTakerRepository extends MongoRepository<CareTaker,String> {
    
    List<CareTaker> findAllByDeletedFlagFalse();

    CareTaker findByCaretakerIdAndDeletedFlagFalse(Long caretakerId);
}

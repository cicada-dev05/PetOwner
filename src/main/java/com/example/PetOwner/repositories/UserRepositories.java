package com.example.PetOwner.repositories;

import com.example.PetOwner.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends MongoRepository<User,String> {

    Optional<User> findByUserId(Long userId);

    User findByUserIdAndDeletedFlagFalse(Long userId);
}

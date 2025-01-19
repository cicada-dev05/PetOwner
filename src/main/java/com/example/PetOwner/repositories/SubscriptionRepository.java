package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriptionRepository extends MongoRepository<Subscription,String> {


    Subscription findBySubscriptionIdAndDeletedFlagFalse(Long subscriptionId);

}

package com.example.PetOwner.service;

import com.example.PetOwner.dtos.SubscriptionDTO;
import com.example.PetOwner.utils.Message;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    List<SubscriptionDTO> getAllSubscriptions();

    SubscriptionDTO getSubscriptionById(Long subscriptionId);

    SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO updateSubscription(Long subscriptionId, SubscriptionDTO subscriptionDTO);

    Message deleteSubscription(Long subscriptionId);
}


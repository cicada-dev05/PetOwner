package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.SubscriptionDTO;
import com.example.PetOwner.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    // Get all subscriptions
    @GetMapping("/getAllSubscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    // Get subscription by ID
    @GetMapping("/getSubscriptionById")
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@RequestParam("subscriptionId") Long subscriptionId) {
        return new ResponseEntity<>(subscriptionService.getSubscriptionById(subscriptionId),HttpStatus.OK);
    }

    // Create a new subscription
    @PostMapping("/createSubscription")
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO createdSubscription = subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }

    // Update an existing subscription
    @PutMapping("/updateSubscription")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@RequestParam("subscriptionId") Long subscriptionId,
                                                              @RequestBody SubscriptionDTO subscriptionDTO) {

        return new ResponseEntity<>(subscriptionService.
                updateSubscription(subscriptionId, subscriptionDTO),HttpStatus.ACCEPTED);
    }

    // Delete a subscription (soft delete)
    @DeleteMapping("/deleteSubscription")
    public ResponseEntity<Void> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }
}

package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    Booking findByBookingIdAndDeletedFlagFalse(Long bookingId);

    List<Booking> findByUserIdAndDeletedFlagFalse(Long userId);
}

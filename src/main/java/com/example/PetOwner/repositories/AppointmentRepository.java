package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment,String> {

    @Query("{ 'userId' : ?0, 'deletedFlag' : false }")
    List<Appointment> findAllByUserId(Long userId);

    Appointment findByAppointmentId(Long appointmentId);

    Appointment findByAppointmentIdAndDeletedFlagFalse(Long appointmentId);
}

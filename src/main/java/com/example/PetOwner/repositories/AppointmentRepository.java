package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment,String> {

    List<Appointment> findAllByUserId(Long userId);

    Appointment findByAppointmentId(Long appointmentId);
}

package com.example.PetOwner.service;

import com.example.PetOwner.dtos.AppointmentDTO;
import com.example.PetOwner.model.Appointment;
import com.example.PetOwner.utils.Message;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<AppointmentDTO> getAllAppointmentsByUserId(Long userId);

    AppointmentDTO getAppointmentById(Long appointmentId);

    AppointmentDTO createAppointment(Appointment appointmentDTO);

    Message updateAppointment(Long appointmentId, Appointment appointmentDTO);

    Message deleteAppointment(Long appointmentId);
}

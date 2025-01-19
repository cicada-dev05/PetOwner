package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.AppointmentDTO;
import com.example.PetOwner.model.Appointment;
import com.example.PetOwner.service.AppointmentService;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {


    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDTO> getAllAppointments(Long userId) {
        return appointmentService.getAllAppointmentsByUserId(userId);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long appointmentId) {
        return new ResponseEntity<>(appointmentService.getAppointmentById(appointmentId), HttpStatus.OK);
    }

    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody Appointment appointmentDTO) {
        return appointmentService.createAppointment(appointmentDTO);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Message> updateAppointment(@PathVariable Long appointmentId,
                                                     @RequestBody Appointment appointmentDTO) {
        return new ResponseEntity<>(appointmentService.updateAppointment(appointmentId,
                appointmentDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Message> deleteAppointment(@PathVariable Long appointmentId) {

        return new ResponseEntity<>(appointmentService.deleteAppointment(appointmentId),HttpStatus.ACCEPTED);
    }
}


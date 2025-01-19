package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.AppointmentDTO;
import com.example.PetOwner.model.Appointment;
import com.example.PetOwner.repositories.AppointmentRepository;
import com.example.PetOwner.service.AppointmentService;
import com.example.PetOwner.utils.Constants;
import com.example.PetOwner.utils.GeneralFunction;
import com.example.PetOwner.utils.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AppointmentDTO> getAllAppointmentsByUserId(Long userId) {
        List<Appointment> appointmentsList = appointmentRepository.findAllByUserId(userId);
        List<AppointmentDTO> appointments = appointmentsList.stream().map(appointment ->
                modelMapper.map(appointment,AppointmentDTO.class)).toList();

        return appointments;
    }

    @Override
    public AppointmentDTO getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);

        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        return appointmentDTO;
    }

    @Override
    public AppointmentDTO createAppointment(Appointment appointmentDTO) {

        appointmentDTO.setCreatedAt(LocalDateTime.now());
        appointmentDTO.setUpdatedTs(LocalDateTime.now());
        appointmentDTO.setDeletedFlag(false);
        appointmentDTO.setAppointmentId(GeneralFunction.generateId());
        Appointment appointmentResponse = appointmentRepository.save(appointmentDTO);

        AppointmentDTO appointment = modelMapper.map(appointmentResponse,AppointmentDTO.class);

        return appointment;
    }

    @Override
    public Message updateAppointment(Long appointmentId, Appointment appointmentDTO) {

        Message message = new Message();
        if(null != appointmentId){
            Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
            if(null != appointment){
                appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
                appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
                appointment.setUpdatedTs(LocalDateTime.now());
                appointment.setStatus(appointment.getStatus());
                appointmentRepository.save(appointment);
                message.setCode(Constants.SUCCESS_CODE);
                message.setMessage(Constants.SUCCESS_MESSAGE);
                return message;
            }
        }
        message.setCode(Constants.FAILURE_CODE);
        message.setMessage(Constants.FAILURE_MESSAGE);

        return message;
    }

    @Override
    public Message deleteAppointment(Long appointmentId) {

        Message message = new Message();
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);

        if(null != appointment){
            appointment.setDeletedFlag(true);
            appointmentRepository.save(appointment);
            message.setCode(Constants.DELETED_CODE);
            message.setMessage(Constants.DELETED_SUCCESSFULLY);

            return message;
        }
        message.setCode(Constants.FAILURE_CODE);
        message.setMessage(Constants.FAILURE_MESSAGE);

        return message;
    }
}

package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.CareTakerDTO;
import com.example.PetOwner.model.CareTaker;
import com.example.PetOwner.repositories.CareTakerRepository;
import com.example.PetOwner.service.CareTakerService;

import com.example.PetOwner.utils.Constants;
import com.example.PetOwner.utils.GeneralFunction;
import com.example.PetOwner.utils.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CareTakerServiceImpl implements CareTakerService {

    @Autowired
    private CareTakerRepository caretakerRepository;

    @Autowired
    private ModelMapper modelMapper;

    private CareTakerDTO convertToDTO(CareTaker caretaker) {
        return modelMapper.map(caretaker, CareTakerDTO.class);
    }

    private CareTaker convertToEntity(CareTakerDTO caretakerDTO) {
        return modelMapper.map(caretakerDTO, CareTaker.class);
    }

    @Override
    public List<CareTakerDTO> getAllCaretakers() {
        List<CareTaker> caretakers = caretakerRepository.findAllByDeletedFlagFalse();
        return caretakers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CareTakerDTO getCaretakerById(Long caretakerId) {
        CareTaker careTaker = caretakerRepository.findByCaretakerIdAndDeletedFlagFalse(caretakerId);
        return modelMapper.map(careTaker,CareTakerDTO.class);
    }

    @Override
    public CareTakerDTO createCaretaker(CareTakerDTO caretakerDTO) {
        CareTaker caretaker = convertToEntity(caretakerDTO);
        caretaker.setCreatedTs(LocalDateTime.now());
        caretaker.setUpdatedTs(LocalDateTime.now());
        caretaker.setDeletedFlag(false);
        caretaker.setCaretakerId(GeneralFunction.generateId());
        CareTaker savedCaretaker = caretakerRepository.save(caretaker);
        return convertToDTO(savedCaretaker);
    }

    @Override
    public CareTakerDTO updateCaretaker(Long caretakerId, CareTakerDTO caretakerDTO) {
        CareTaker caretaker = caretakerRepository.findByCaretakerIdAndDeletedFlagFalse(caretakerId);
        if(null != caretaker){
            caretaker.setName(caretakerDTO.getName());
            caretaker.setPhone(caretakerDTO.getPhone());
            caretaker.setEmail(caretakerDTO.getEmail());
            caretaker.setLocation(caretakerDTO.getLocation());
            caretaker.setRating(caretakerDTO.getRating());
            caretaker.setUpdatedTs(LocalDateTime.now());
            CareTaker updatedCaretaker = caretakerRepository.save(caretaker);
            return convertToDTO(updatedCaretaker);
        }
        return null;
    }

    @Override
    public Message deleteCaretaker(Long caretakerId) {

        Message message = new Message();
        CareTaker caretaker = caretakerRepository.findByCaretakerIdAndDeletedFlagFalse(caretakerId);
        if(null != caretaker){
            caretaker.setDeletedFlag(true);
            caretaker.setUpdatedTs(LocalDateTime.now());
            caretakerRepository.save(caretaker);
            message.setCode(Constants.DELETED_CODE);
            message.setMessage(Constants.DELETED_SUCCESSFULLY);
            return  message;
        }
        message.setCode(Constants.FAILURE_CODE);
        message.setMessage(Constants.FAILURE_MESSAGE);
        return message;
    }
}



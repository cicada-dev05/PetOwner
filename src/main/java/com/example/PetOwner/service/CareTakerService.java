package com.example.PetOwner.service;

import com.example.PetOwner.dtos.CareTakerDTO;
import com.example.PetOwner.utils.Message;

import java.util.List;
import java.util.Optional;

public interface CareTakerService {

    List<CareTakerDTO> getAllCaretakers();

    CareTakerDTO getCaretakerById(Long careTakerId);

    CareTakerDTO createCaretaker(CareTakerDTO careTakerDTO);

    CareTakerDTO updateCaretaker(Long careTakerId, CareTakerDTO caretakerDTO);

    Message deleteCaretaker(Long caretakerId);
}



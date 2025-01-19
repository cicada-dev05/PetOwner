package com.example.PetOwner.service;

import com.example.PetOwner.model.PetService;
import com.example.PetOwner.utils.Message;

import java.util.List;

public interface PetServiceService {

    public List<PetService> getAllServices();

    public PetService getServiceById(Long serviceId);

    public PetService createService(PetService service);

    public PetService updateService(Long serviceId, PetService serviceDetails);

    public Message deleteService(Long serviceId);
}

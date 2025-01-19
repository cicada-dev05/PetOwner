package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.ServiceBookRequestDTO;
import com.example.PetOwner.model.PetService;
import com.example.PetOwner.service.PetServiceService;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class PetServiceController {

    @Autowired
    PetServiceService service;

    @PostMapping("/createService")
    public PetService createService(PetService petService){
        return service.createService(petService);
    }

    @GetMapping("/getAllServices")
    public List<PetService> getAllServices(){
        return service.getAllServices();
    }

    @GetMapping("getServiceById")
    public PetService getServiceById(Long serviceId){
        return service.getServiceById(serviceId);
    }

    @PostMapping("/updateServiceByServiceId")
    public PetService updateService(Long serviceId, PetService serviceDetails){
        return service.updateService(serviceId,serviceDetails);
    }

    @PostMapping("deleteServiceById")
    public Message deleteService(Long serviceId){
        return service.deleteService(serviceId);
    }
}

package com.example.PetOwner.serviceImp;

import com.example.PetOwner.model.PetService;
import com.example.PetOwner.repositories.PetServiceRepository;
import com.example.PetOwner.service.PetServiceService;
import com.example.PetOwner.utils.Constants;
import com.example.PetOwner.utils.GeneralFunction;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceServiceImpl implements PetServiceService {

    @Autowired
    private PetServiceRepository serviceRepository;

    // Method to get all services
    public List<PetService> getAllServices() {
        return serviceRepository.findAll();
    }

    // Method to get a service by its ID
    public PetService getServiceById(Long serviceId) {

        return serviceRepository.findByServiceId(serviceId);
    }

    // Method to create a new service
    public PetService createService(PetService service) {
        service.setCreatedTs(LocalDateTime.now());
        service.setUpdatedTs(LocalDateTime.now());
        service.setDeletedFlag(false);
        Long serviceId = GeneralFunction.generateId();
        service.setServiceId(serviceId);
        PetService petService = serviceRepository.save(service);
        return serviceRepository.save(petService);

    }

    // Method to update an existing service
    public PetService updateService(Long serviceId, PetService serviceDetails) {
        PetService updatedService = serviceRepository.findByServiceId(serviceId);
            updatedService.setName(serviceDetails.getName());
            updatedService.setDescription(serviceDetails.getDescription());
            updatedService.setPrice(serviceDetails.getPrice());
            updatedService.setDuration(serviceDetails.getDuration());
            return serviceRepository.save(updatedService);
    }

    // Method to delete a service by its ID
    public Message deleteService(Long serviceId) {

        Message message = new Message();
        if(null != serviceId){
            PetService petService = serviceRepository.findByServiceId(serviceId);
            if(null != petService){
                petService.setDeletedFlag(true);
                message.setCode(Constants.DELETED_CODE);
                message.setMessage(Constants.DELETED_SUCCESSFULLY);
                return message;
            }
        }
        message.setCode(Constants.FAILURE_CODE);
        message.setMessage(Constants.FAILURE_MESSAGE);
        return message;
    }
}


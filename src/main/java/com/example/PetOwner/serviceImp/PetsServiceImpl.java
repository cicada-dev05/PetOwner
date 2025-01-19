package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.PetsDTO;
import com.example.PetOwner.model.Pets;
import com.example.PetOwner.repositories.PetsRepositories;
import com.example.PetOwner.service.PetsService;
import com.example.PetOwner.utils.Constants;
import com.example.PetOwner.utils.GeneralFunction;
import com.example.PetOwner.utils.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PetsServiceImpl implements PetsService {

    @Autowired
    PetsRepositories petsRepositories;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PetsDTO addPets(Pets petsRequest) {
        Pets pets = petsRepositories.save(petsRequest);
        pets.setPetId(GeneralFunction.generateId());
        pets.setCreatedTs(LocalDateTime.now());
        pets.setUpdatedTs(LocalDateTime.now());
        pets.setDeletedFlag(false);
        petsRepositories.save(pets);
        PetsDTO petsDTO = modelMapper.map(pets,PetsDTO.class);
        return petsDTO;
    }

    @Override
    public PetsDTO getPets(Long petId) {
        Pets pets = petsRepositories.findByPetId(petId);
        PetsDTO petsDTO = modelMapper.map(pets,PetsDTO.class);
        return petsDTO;
    }

    @Override
    public List<PetsDTO> getAllPets(Long ownerId) {

        List<Pets> petsList = petsRepositories.findByOwnerId(ownerId);
        List<PetsDTO> petsDTOS = petsList.stream().map(pets -> modelMapper.map(pets,PetsDTO.class)).toList();
        return petsDTOS;
    }

    @Override
    public Message updatePets(PetsDTO petsDTO) {
        Pets pets = petsRepositories.findByPetId(petsDTO.getPetId());
        Message message = new Message();
        if(null != pets){
            pets.setUpdatedTs(LocalDateTime.now());
            pets.setWeight(petsDTO.getWeight());
            pets.setType(petsDTO.getType());
            pets.setMedicalHistory(pets.getMedicalHistory());
            pets.setAge(petsDTO.getAge());
            pets.setName(pets.getName());
            petsRepositories.save(pets);
            message.setCode(Constants.SUCCESS_CODE);
            message.setMessage(Constants.SUCCESS_MESSAGE);
            return message;
        }

        message.setMessage(Constants.FAILURE_MESSAGE);
        message.setCode(Constants.FAILURE_CODE);

        return message;

    }

    @Override
    public Message deletePets(Long petId) {

        Pets pets = petsRepositories.findByPetId(petId);
        Message message = new Message();

        if(null != pets && !pets.getDeletedFlag()){
            pets.setDeletedFlag(true);
            petsRepositories.save(pets);
            message.setCode(Constants.DELETED_CODE);
            message.setMessage(Constants.DELETED_SUCCESSFULLY);
            return message;
        }
        message.setCode(Constants.FAILURE_CODE);
        message.setMessage(Constants.FAILURE_MESSAGE);
        return  message;
    }
}

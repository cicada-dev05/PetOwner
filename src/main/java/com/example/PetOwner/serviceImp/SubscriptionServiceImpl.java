package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.SubscriptionDTO;
import com.example.PetOwner.model.Subscription;
import com.example.PetOwner.repositories.SubscriptionRepository;
import com.example.PetOwner.service.SubscriptionService;
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
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ModelMapper modelMapper; // For mapping between entity and DTO

    private SubscriptionDTO convertToDTO(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }

    private Subscription convertToEntity(SubscriptionDTO subscriptionDTO) {
        return modelMapper.map(subscriptionDTO, Subscription.class);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAllAndDeletedFlagFalse();
        return subscriptions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO getSubscriptionById(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.
                findBySubscriptionIdAndDeletedFlagFalse(subscriptionId);
        return modelMapper.map(subscription,SubscriptionDTO.class);
    }

    @Override
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = convertToEntity(subscriptionDTO);
        subscription.setCreatedTs(LocalDateTime.now());
        subscription.setUpdatedTs(LocalDateTime.now());
        subscription.setDeletedFlag(false);
        subscription.setSubscriptionId(GeneralFunction.generateId());
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return convertToDTO(savedSubscription);
    }

    @Override
    public SubscriptionDTO updateSubscription(Long subscriptionId, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findBySubscriptionIdAndDeletedFlagFalse(subscriptionId);
            subscription.setPlanName(subscriptionDTO.getPlanName());
            subscription.setPrice(subscriptionDTO.getPrice());
            subscription.setStartDate(subscriptionDTO.getStartDate());
            subscription.setEndDate(subscriptionDTO.getEndDate());
            subscription.setStatus(subscriptionDTO.getStatus());
            subscription.setUpdatedTs(LocalDateTime.now());
            Subscription updatedSubscription = subscriptionRepository.save(subscription);
            return convertToDTO(updatedSubscription);
    }

    @Override
    public Message deleteSubscription(Long subscriptionId) {

        Message message = new Message();
        Subscription subscription = subscriptionRepository.
                findBySubscriptionIdAndDeletedFlagFalse(subscriptionId);
        if(null != subscription){
            subscription.setDeletedFlag(true);
            subscription.setUpdatedTs(LocalDateTime.now());
            subscriptionRepository.save(subscription);
            message.setCode(Constants.DELETED_CODE);
            message.setMessage(Constants.DELETED_SUCCESSFULLY);
            return message;
        };
        message.setCode(Constants.FAILURE_CODE);
        message.setMessage(Constants.FAILURE_MESSAGE);
        return message;
    }
}

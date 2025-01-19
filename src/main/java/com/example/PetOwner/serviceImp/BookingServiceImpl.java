package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.BookingDTO;
import com.example.PetOwner.dtos.BookingResponseDTO;
import com.example.PetOwner.dtos.ServiceBookRequestDTO;
import com.example.PetOwner.model.Booking;
import com.example.PetOwner.model.PetService;
import com.example.PetOwner.model.User;
import com.example.PetOwner.repositories.BookingRepository;
import com.example.PetOwner.repositories.PetServiceRepository;
import com.example.PetOwner.repositories.PetsRepositories;
import com.example.PetOwner.repositories.UserRepositories;
import com.example.PetOwner.service.BookingService;
import com.example.PetOwner.utils.Constants;
import com.example.PetOwner.utils.GeneralFunction;
import com.example.PetOwner.utils.Message;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepositories userRepositories;

    @Autowired
    PetsRepositories petsRepositories;

    @Autowired
    PetServiceRepository petServiceRepository;

    private BookingDTO convertToDTO(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO, Booking.class);
    }

    @Override
    public Message bookService(ServiceBookRequestDTO bookingDTO) {

        Message message = new Message();
        Booking booking = new Booking();
        try{
            User user = userRepositories.findByUserIdAndDeletedFlagFalse(bookingDTO.getUserId());

            if(null != user){
                PetService petService = petServiceRepository.findByServiceIdAndDeletedFlagFalse(bookingDTO.getServiceId());
                if(null != petService){
                    booking.setBookingId(GeneralFunction.generateId());
                    booking.setUserId(bookingDTO.getUserId());
                    booking.setPetId(bookingDTO.getPetId());
                    booking.setServiceId(petService.getServiceId());
                    booking.setServiceName(petService.getName());
                    booking.setDate(bookingDTO.getBookDate());
                    booking.setDeletedFlag(false);
                    booking.setCreatedTs(LocalDateTime.now());
                    booking.setUpdatedTs(LocalDateTime.now());
                    bookingRepository.save(booking);
                }
            }
            message.setMessage("Service booked successfully");
            message.setCode(booking.getBookingId());
            return message;
        }catch (Exception e){
            LOGGER.error("Error occured while booking...{}",e.getMessage());
            message.setCode(Constants.FAILURE_CODE);
            message.setMessage(Constants.FAILURE_MESSAGE);
            return message;
        }
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findByBookingIdAndDeletedFlagFalse(bookingId);
        if (booking == null) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }
        return convertToDTO(booking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Message deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findByBookingIdAndDeletedFlagFalse(bookingId);
        if (booking == null) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }

        booking.setDeletedFlag(true);
        booking.setUpdatedTs(LocalDateTime.now());
        bookingRepository.save(booking);
        Message message = new Message();
        message.setMessage("Booking cancelled successfully");
        message.setCode(Constants.SUCCESS_CODE);
        return  message;
    }

    @Override
    public List<BookingResponseDTO> getBookingByUserId(Long userId) {

        List<Booking> bookings = bookingRepository.findByUserIdAndDeletedFlagFalse(userId);
        List<BookingResponseDTO> bookingResponseDTOS = List.of();
        if(null != bookings){
            bookingResponseDTOS = bookings.stream()
                    .map(booking -> {
                        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
                        bookingResponseDTO.setBookingId(booking.getBookingId());
                        bookingResponseDTO.setServiceName(booking.getServiceName());
                        bookingResponseDTO.setDate(booking.getDate());
                        return bookingResponseDTO;
                    })
                    .collect(Collectors.toList());
        }
        return bookingResponseDTOS;
    }
}


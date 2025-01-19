package com.example.PetOwner.service;

import com.example.PetOwner.dtos.BookingDTO;
import com.example.PetOwner.dtos.BookingResponseDTO;
import com.example.PetOwner.dtos.ServiceBookRequestDTO;
import com.example.PetOwner.utils.Message;

import java.util.List;

public interface BookingService {


    Message bookService(ServiceBookRequestDTO serviceBookRequestDTO);


    BookingDTO getBookingById(Long bookingId);

    List<BookingDTO> getAllBookings();

    Message deleteBooking(Long bookingId);

    List<BookingResponseDTO> getBookingByUserId(Long userId);
}

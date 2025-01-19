package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.BookingDTO;
import com.example.PetOwner.dtos.BookingResponseDTO;
import com.example.PetOwner.dtos.ServiceBookRequestDTO;
import com.example.PetOwner.service.BookingService;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping("/createBooking")
    public ResponseEntity<Message> createBooking(@RequestBody ServiceBookRequestDTO bookingDTO) {
         return new ResponseEntity<>(bookingService.bookService(bookingDTO),HttpStatus.ACCEPTED);

    }

    // Get booking by ID
    @GetMapping("/getBookingById")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable("/bookingId") Long bookingId) {
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/getBookingByUserId")
    public ResponseEntity<List<BookingResponseDTO>> getBookingByUserId(@RequestParam("userId") Long userId){
        List<BookingResponseDTO> bookingResponseDTOS = bookingService.getBookingByUserId(userId);
        return ResponseEntity.ok(bookingResponseDTOS);
    }

    // Get all bookings
    @GetMapping("/getAllBookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Delete a booking (soft delete)
    @DeleteMapping("/deleteBooking")
    public ResponseEntity<Message> deleteBooking(@RequestParam("bookingId") Long bookingId) {
        return new ResponseEntity<>(bookingService.deleteBooking(bookingId),HttpStatus.OK);
    }
}

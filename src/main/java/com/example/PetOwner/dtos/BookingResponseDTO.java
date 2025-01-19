package com.example.PetOwner.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingResponseDTO {

    private Long bookingId;

    private String serviceName;

    private LocalDate Date;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }
}

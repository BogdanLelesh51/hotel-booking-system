package com.hotel.service;

import com.hotel.model.Booking;
import com.hotel.repository.BookingRepository;

public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }


    public void createBooking(Booking booking) {

    for (Booking existingBooking : repository.findAll()) {

        boolean sameRoom =
                existingBooking.getRoom().getNumber()
                        == booking.getRoom().getNumber();

        boolean datesOverlap =
                booking.getStartDate().isBefore(existingBooking.getEndDate())
                &&
                booking.getEndDate().isAfter(existingBooking.getStartDate());

        if (sameRoom && datesOverlap) {
            throw new IllegalStateException(
                    "Room is already booked for these dates"
            );
        }
    }

    repository.save(booking);
}
}
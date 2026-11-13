package com.hotel.service;

import com.hotel.model.Booking;
import com.hotel.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public void createBooking(Booking booking) {

        if (booking == null) {
            LOGGER.error("Booking operation failed: received null booking");
            throw new IllegalArgumentException("Booking must not be null");
        }

        LOGGER.info("Booking request received for room {}", booking.getRoom().getNumber());

        for (Booking existingBooking : repository.findAll()) {

            boolean sameRoom = existingBooking.getRoom().getNumber() == booking.getRoom().getNumber();

            boolean datesOverlap = booking.getStartDate().isBefore(existingBooking.getEndDate())
                    && booking.getEndDate().isAfter(existingBooking.getStartDate());

            if (sameRoom && datesOverlap) {
                LOGGER.warn("Room {} is already booked for the requested dates", booking.getRoom().getNumber());
                throw new IllegalStateException("Room is already booked for these dates");
            }
        }

        try {
            repository.save(booking);
            LOGGER.info(
                    "Booking created successfully: room {}, guest {}",
                    booking.getRoom().getNumber(),
                    booking.getGuest().getName()
            );
        } catch (RuntimeException e) {
            LOGGER.error("Booking operation failed while saving booking for room {}",
                    booking.getRoom().getNumber(), e);
            throw e;
        }
    }
}

package com.hotel.repository;

import com.hotel.model.Booking;
import java.util.List;

public interface BookingRepository {

    void save(Booking booking);

    List<Booking> findAll();
}
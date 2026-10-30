package com.hotel.model;

import java.time.LocalDate;

public class Booking {

    private int id;
    private Guest guest;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(int id, Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
package com.hotel.model;

public class Room {
    private int number;
    private String type;

    public Room(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }
    public String getType() {
        return type;
    }
}
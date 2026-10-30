package com.hotel.model;

public class Guest {
    private int id;
    private String name;

    public Guest(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}


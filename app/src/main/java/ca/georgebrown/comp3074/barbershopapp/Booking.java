package ca.georgebrown.comp3074.barbershopapp;

import java.io.Serializable;

public class Booking implements Serializable {
    private String date;
    private String service;
    private String barber;
    private String time;

    // Constructors, getters, and setters

    public Booking(String date, String service, String barber, String time) {
        this.date = date;
        this.service = service;
        this.barber = barber;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBarber() {
        return barber;
    }

    public void setBarber(String barber) {
        this.barber = barber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

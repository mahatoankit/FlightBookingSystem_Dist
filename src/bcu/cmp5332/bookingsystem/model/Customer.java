package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings = new ArrayList<>();
    
    // TODO: implement constructor here
    public Customer(int id, String name, String phone) {
    	this.id = id;
    	this.name = name;
    	this.phone = phone;    	
    }
    
    // TODO: implementation of Getter and Setter methods
    public int getId() {
    	return id;
    }
    
    public void setId(int value) {
    	this.id = value;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String string) {
    	this.name = string;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String string) {
    	this.phone = string;
    }
    
    public void addBooking(Booking booking) {
        // TODO: implementation here
    }
}

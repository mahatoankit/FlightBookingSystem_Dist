package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
    	this.customer = customer;
    	this.flight = flight;
    	this.bookingDate = bookingDate;
    }
    
    // TODO: implementation of Getter and Setter methods
    public Customer getCustomer() {
    	return customer;
    }
    
    public void setCustomer(int id, String name, String phone) {
    	this.customer = new customer(id, name, phone);
    }
}

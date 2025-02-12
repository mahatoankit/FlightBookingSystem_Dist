package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String mail;
    private boolean isDeleted = false;
    private final List<Booking> bookings = new ArrayList<>();
    
    // TODO: implement constructor here
    public Customer(int id, String name, String phone, String mail, boolean isDeleted) {
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    	this.mail = mail;
    	this.isDeleted = isDeleted;
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
    	bookings.add(booking);
    }
    
    public void setDeleted() {
    	this.isDeleted = true;
    }
    
    public boolean getDeleted() {
    	return this.isDeleted;
    }

    public String getDetailsShort() {
        return "Customer #" + this.id + " - " + this.name +" - "+ this.mail + " - " + this.phone;
    }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
    public void showDetails() {
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phone);

        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println("  Booking ID: " + booking.getId());
            System.out.println("  Outbound Flight: " + booking.getOutboundFlight().getFlightNumber() +
                    " From: " + booking.getOutboundFlight().getOrigin() +
                    " To: " + booking.getOutboundFlight().getDestination() +
                    " Date: " + booking.getOutboundFlight().getDepartureDate() +
                    " Price: " + booking.getOutboundFlight().getPrice());
            if (booking.getReturnFlight() != null) {
                System.out.println("  Return Flight: " + booking.getReturnFlight().getFlightNumber() +
                        " From: " + booking.getReturnFlight().getOrigin() +
                        " To: " + booking.getReturnFlight().getDestination() +
                        " Date: " + booking.getReturnFlight().getDepartureDate() +
                        " Price: " + booking.getReturnFlight().getPrice());
            }
        }
    }
}

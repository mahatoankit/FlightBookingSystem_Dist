package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.parse("2020-11-11");
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();

    public LocalDate getSystemDate() {
        return systemDate;
    }

    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }

    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer to retrieve
     * @return The customer with the specified ID
     * @throws FlightBookingSystemException If no customer exists with the given ID
     */
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID: " + id);
        }
        return customers.get(id);
    }
    
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    public void addCustomer(Customer customer) {
        // TODO: implementation here
    	if (customers.containsKey(customer.getId())) {
    		throw new IllegalArgumentException("Duplicate customer ID.");
    	}
    	customers.put(customer.getId(), customer);

    }

	public List<Customer> getCustomers() {
		List<Customer> cus = new ArrayList<>(customers.values());
		return Collections.unmodifiableList(cus);
	}
	
	public void issueBooking(Customer customer, Flight flight, LocalDate bookingDate) throws FlightBookingSystemException {
	        if (!customers.containsValue(customer)) {
	            throw new FlightBookingSystemException("Customer does not exist in the system.");
	        }
	        if (!flights.containsValue(flight)) {
	            throw new FlightBookingSystemException("Flight does not exist in the system.");
	        }
	        if (flight.getPassengerCount() >= flight.getCapacity()) {
	            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
	        }
	        Booking booking = new Booking(customer, flight);
	        customer.addBooking(booking);
	        flight.addPassenger(customer); // Ensure the flight tracks its passengers
	    }


}

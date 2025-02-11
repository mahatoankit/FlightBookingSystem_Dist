package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The AddCustomer class represents a command to add a new customer to the flight booking system.
 * It implements the Command interface and requires execution within a FlightBookingSystem instance.
 * 
 * <p>The customer is identified by their name, phone number, and email address. Upon execution,
 * the system assigns a unique ID to the new customer, adds them to the system, and stores the updated
 * data using FlightBookingSystemData.
 * 
 * <p>Example usage:
 * <pre>{@code
 * String name = "John Doe";
 * String mail = "john.doe@example.com";
 * String phone = "1234567890";
 * Command addCustomerCommand = new AddCustomer(name, mail, phone);
 * addCustomerCommand.execute(flightBookingSystem);
 * }</pre>
 */
public class AddCustomer implements Command {

    private final String name;
    private final String mail;
    private final String phone;

    /**
     * Constructs an AddCustomer command with the specified name, email address, and phone number.
     */
    public AddCustomer(String name, String mail, String phone) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }
    
	/**
     * Executes the AddCustomer command within the provided FlightBookingSystem instance.
     * Generates a unique ID for the new customer, creates a Customer object with the provided
     * name, phone number, email, and adds it to the system using flightBookingSystem.
     * The updated system data is then stored using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the customer is to be added
     * @throws FlightBookingSystemException If there is an issue adding the customer to the system
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        int maxId = 0;
        for (Customer customer : flightBookingSystem.getCustomers()) {
            if (customer.getId() > maxId) {
                maxId = customer.getId();
            }
        }

        Customer customer = new Customer(++maxId, name, mail, phone, false);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer #" + customer.getId() + " added.");

        FlightBookingSystemData.store(flightBookingSystem);
    }
}

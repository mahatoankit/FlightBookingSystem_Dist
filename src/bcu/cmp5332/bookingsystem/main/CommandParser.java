package bcu.cmp5332.bookingsystem.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.ListCustomers;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.commands.AddBooking;

public class CommandParser {
    
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            
            if (cmd.equals("addflight")) {
          
                System.out.print("Flight Number: ");
                String flighNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();

                LocalDate departureDate = parseDateWithAttempts(reader);

                return new AddFlight(flighNumber, origin, destination, departureDate, 0, 0);
            } else if (cmd.equals("addcustomer")) {
            	
            	System.out.print("Customer name: ");
                String name = reader.readLine();
                System.out.print("Customer phone: ");
                String phone = reader.readLine();
                System.out.print("Customer email: ");
                String email = reader.readLine();
                return new AddCustomer(name, phone, email);
                
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showflight")) {
                    return new ShowFlight(id);
                } else if (cmd.equals("showcustomer")) {
               return new ShowCustomer(id);
                }
            } else if (parts.length == 3) {
                int custID = Integer.parseInt(parts[1]);
                int fliID = Integer.parseInt(parts[2]);
                LocalDate date = parseDateWithAttempts(reader);
                
                if (cmd.equals("addbooking")) {
                    return new AddBooking(custID, fliID, date);
                    
                } else if (cmd.equals("editbooking")) {
               	 Scanner scanner = new Scanner(System.in);
                 System.out.println("Enter bookingID:");
                 int bookingID = scanner.nextInt();
                 scanner.nextLine();

                 System.out.println("Enter newFlightNumber:");
                 String newFlightNumber = scanner.nextLine();

                 System.out.println("Enter new booking date (YYYY-MM-DD):");
                 String dateString = scanner.nextLine();
                 LocalDate newBookDate = LocalDate.parse(dateString);
                 
                 return new EditBooking(bookingID, newFlightNumber, newBookDate);
                } else if (cmd.equals("cancelbooking")) {
                	int customerID = Integer.parseInt(parts[1]);
                	int flightID = Integer.parseInt(parts[2]);
                    return new CancelBooking(customerID, flightID);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * The {@code AddFlight} class represents a command to add a new flight 
 * to the flight booking system.
 * 
 * <p>It implements the {@link Command} interface and executes within a 
 * {@link FlightBookingSystem} instance. This command creates a new 
 * {@link Flight} object with the provided details, assigns a unique ID, 
 * and adds it to the system. The updated flight data is then saved 
 * using {@link FlightBookingSystemData#store(FlightBookingSystem)}.</p>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * FlightBookingSystem system = new FlightBookingSystem();
 * Command addFlightCommand = new AddFlight(
 *     "BA123", "London", "New York",
 *     LocalDate.of(2024, 6, 1), 250, 500.00
 * );
 * addFlightCommand.execute(system);
 * }</pre>
 */
public class AddFlight implements Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int capacity;
    private final double price;

    /**
     * Constructs an {@code AddFlight} command with the specified flight details.
     *
     * @param flightNumber   The flight number (e.g., "BA123").
     * @param origin         The departure location of the flight.
     * @param destination    The arrival location of the flight.
     * @param departureDate  The scheduled departure date.
     * @param capacity       The maximum number of passengers allowed.
     * @param price          The ticket price for the flight.
     */
    public AddFlight(String flightNumber, String origin, String destination, 
                     LocalDate departureDate, int capacity, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
    }

    /**
     * Executes the {@code AddFlight} command within the given 
     * {@link FlightBookingSystem} instance.
     * 
     * <p>This method:
     * <ul>
     *   <li>Generates a unique flight ID.</li>
     *   <li>Creates a new {@link Flight} object with the specified details.</li>
     *   <li>Adds the flight to the booking system.</li>
     *   <li>Persists the updated data to storage.</li>
     * </ul>
     * </p>
     *
     * @param flightBookingSystem The flight booking system where the flight is added.
     * @throws FlightBookingSystemException If an error occurs while adding the flight.
     * @throws IOException If an I/O error occurs while storing data.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) 
            throws FlightBookingSystemException, IOException {
        // Get the highest existing flight ID and increment it for uniqueness
        List<Flight> flights = flightBookingSystem.getFlights();
        int newFlightId = flights.stream().mapToInt(Flight::getId).max().orElse(0) + 1;

        // Create a new Flight object
        Flight flight = new Flight(newFlightId, flightNumber, origin, destination, departureDate, (int) price, capacity, false);

        // Add the flight to the system
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added successfully.");

        // Persist changes to storage
        FlightBookingSystemData.store(flightBookingSystem);
    }
}

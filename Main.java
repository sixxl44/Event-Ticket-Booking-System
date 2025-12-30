package Group4_3w7;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static SinglyLinkedList<Customer> custList = new SinglyLinkedList<>();
    public static LLQueue<Ticket> TQueue = new LLQueue<>();
    public static LLStack<Booking> BStack = new LLStack<>();
    public static ArrayList<Event> eventList = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        AvailableEvent();
        boolean running = true;

        while (running) {
            Menu();
            int choice = in.nextInt();
            in.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    AddCustomer();
                    break;
                case 2:
                    reserveBooking();
                    break;
                case 3:
                    System.out.println("Do you want to cancel a Booking or a Ticket?");
                    char cancel = in.next().charAt(0);
                    switch (cancel) {
                        case 'B':
                        case 'b':
                            cancelBooking();
                            break;
                        case 'T':
                        case 't':
                            cancelTicket();
                            break;
                    }
                    break;
                case 4:
                    displayAllBookings();
                    break;
                case 5:
                    System.out.println("Do you want to Search for a Booking or a Ticket?");
                    char find = in.next().charAt(0);
                    switch (find) {
                        case 'B':
                        case 'b':
                            findBookingById();
                            break;
                        case 'T':
                        case 't':
                            findTicketByNumber();
                            break;
                    }
                    break;
                case 6:
                    viewMostBookedEvent();
                    break;
                case 7:
                    modifyCustomer();
                    break;
                case 0:
                    System.out.println("Thank You!!");
                    running = false;
                    break;
                default:
                    System.out.println("\u001B[41m" + "Invalid choice. Please try again.");
            }
        }
    }

    public static void Menu() {
        System.out.println("\u001B[45m" + "=== Ticket Reservation System === \n"
                + "1. New Customer\n"
                + "2. Reserve  booking \n"
                + "3. Cancel booking/ticket \n"
                + "4. Display my bookings \n"
                + "5. Find booking \n"
                + "6. View Most Booked Events\n"
                + "7. Modify/View customer\n"
                + "0. Exit \n");

        System.out.print("Enter your choice : ");

    }

    // 1. Add New Customer
    public static void AddCustomer() {
        System.out.print("Enter Your Full Name: ");
        String fullName = in.nextLine();
        System.out.print("Enter Your ID: ");
        int id = in.nextInt();
        System.out.print("Enter Your Age: ");
        int age = in.nextInt();
        in.nextLine(); // Consume newline
        System.out.print("Enter Your Gender: ");
        String gender = in.nextLine();
        System.out.print("Enter Your Email: ");
        String email = in.nextLine();
        System.out.print("Enter Your Phone Number: ");
        String phoneNumber = in.nextLine();

        Customer customer = new Customer(fullName, id, age, gender, email, phoneNumber);
        custList.addCustomer(customer);
        System.out.println("");
    }

    // 2. Reserve Booking
    public static void reserveBooking() {
        System.out.print("Enter Your Customer ID: ");
        int custID = in.nextInt();
        Customer customer = (Customer) custList.Sreach(custID);

        if (customer == null) {
            System.out.println("\u001B[41m" + "Customer not found.");
            return;
        }

        System.out.println("Do you want to book a Movie or a Sport event?");
        char eventTypeChoice = in.next().charAt(0);

        String eventType;
        switch (eventTypeChoice) {
            case 'M':
            case 'm':
                eventType = "Movie";
                break;
            case 'S':
            case 's':
                eventType = "Sport";
                break;
            default:
                System.out.println("\u001B[41m" + "Invalid choice. Please enter Movie or Sport.");
                return;
        }

        System.out.println("\nAvailable " + eventType + " Events:");
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getEvent_Name().equalsIgnoreCase(eventType)) {
                filteredEvents.add(event);
                System.out.println((filteredEvents.size()) + ". " + event);
            }
        }

        if (filteredEvents.isEmpty()) {
            System.out.println("\u001B[41m" + "No " + eventType + " events available.");
            return;
        }

        System.out.print("\nChoose an event by entering the event number: ");
        int eventChoice = in.nextInt();

        if (eventChoice < 1 || eventChoice > filteredEvents.size()) {
            System.out.println("\u001B[41m" + "Invalid event choice.");
            return;
        }

        Event chosenEvent = filteredEvents.get(eventChoice - 1);
        Ticket[] availableTickets = chosenEvent.getTicket();

        System.out.println("Available Seats for " + eventType + " Event:");
        for (Ticket ticket : availableTickets) {
            if (ticket.isIsAvailable()) {
                System.out.println("Seat Number: " + ticket.getSeatNumber() + " - Price: $" + ticket.getPrice());
            }
        }

        System.out.print("\nEnter the number of seats to be booked: ");
        int numSeats = in.nextInt();

        if (numSeats > Bookingable.MAX_TICKETS) {
            System.out.println("\u001B[41m" + "You cannot book more than " + Bookingable.MAX_TICKETS + " tickets at once.");
            return;
        }

        Ticket[] selectedTickets = new Ticket[numSeats];
        for (int i = 0; i < numSeats; i++) {
            System.out.print("Enter Seat Number to reserve: ");
            int seatNumber = in.nextInt();
            boolean seatFound = false;

            for (Ticket ticket : availableTickets) {
                if (ticket.getSeatNumber() == seatNumber && ticket.isIsAvailable()) {
                    ticket.setIsAvailable(false); // Mark ticket as reserved
                    ticket.setTicketNumber(generateTicketNumber()); // Assign ticket number
                    selectedTickets[i] = ticket;
                    seatFound = true;
                    break;
                }
            }

            if (!seatFound) {
                System.out.println("Seat number " + seatNumber + " is not available. Please choose another seat.");
                i--; // Retry current seat selection
            }
        }

        Booking booking = new Booking(Booking.getNum(), customer, selectedTickets, true, "Confirmed", chosenEvent);
        BStack.push(booking);

        displayBookingDetails(booking);

        System.out.println("");
        System.out.println("Total Price: $" + booking.calcTotal());
        booking.Booking();
    }

    // 3. Cancel Booking
    public static void cancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");
        int bookingID = in.nextInt();
        Booking booking = (Booking) BStack.findBookingByID(bookingID);

        if (booking != null) {
            // Cancel all tickets in the booking
            Ticket[] tickets = booking.getTicket();
            
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    ticket.setIsAvailable(true);  // Set the ticket as available
                }
            }

            booking.cancel();
        } else {
            System.out.println("Booking not found.");
        }
    }

    // 3. Cancel Ticket
    public static void cancelTicket() {
        System.out.print("Enter the ticket number to cancel: ");
        String ticketNumber = in.next();

        Ticket foundTicket = TQueue.findTicketByNumber(ticketNumber);

        if (foundTicket == null) {
            System.out.println("\u001B[41m" + "Ticket with ticket number " + ticketNumber + " was not found.");
            return;
        }

        boolean isCanceled = TQueue.cancelTicket(ticketNumber);

        if (isCanceled) {
            System.out.println("Ticket with ticket number " + ticketNumber + " has been successfully canceled.");

            LLStack<Booking> tempStack = new LLStack<>();
            Booking relatedBooking = null;

            while (!BStack.isEmpty()) {
                Booking booking = BStack.pop();
                boolean ticketFoundInBooking = false;

                // Check if the ticket is part of this booking
                Ticket[] tickets = booking.getTicket();
                for (int i = 0; i < tickets.length; i++) {
                    if (tickets[i] != null && tickets[i].getTicketNumber().equals(ticketNumber)) {
                        tickets[i] = null; // Remove the ticket from the booking
                        ticketFoundInBooking = true;
                        break;
                    }
                }

                if (ticketFoundInBooking) {
                    relatedBooking = booking;
                }

                tempStack.push(booking);
            }

            // Restore the original stack state
            while (!tempStack.isEmpty()) {
                BStack.push(tempStack.pop());
            }

        } else {
            System.out.println("\u001B[41m" + "Ticket with ticket number " + ticketNumber + " was already canceled.");
        }
    }

    // 4. Display User Bookings
    public static void displayAllBookings() {
        System.out.print("Enter your ID: ");
        int Id = in.nextInt();
        Customer customer = (Customer) custList.Sreach(Id);

        if (customer == null) {
            System.out.println("\u001B[41m" + "Customer not found.");
            return;
        }
        System.out.println("\nDisplaying bookings for user ID: " + Id);
        BStack.displayUserBookingHistory(Id);
    }

    // 5. Find Booking by ID
    public static void findBookingById() {
        System.out.print("Enter Booking ID to search: ");
        int bookingID = in.nextInt();
        Booking booking = (Booking) BStack.findBookingByID(bookingID);

        // Use the helper method to display the booking and ticket details
        displayBookingDetails(booking);
    }

    // 5. Find Ticket by Number
    public static void findTicketByNumber() {
        System.out.print("Enter the ticket number to search: ");
        String ticketNumber = in.next();

        Ticket foundTicket = TQueue.findTicketByNumber(ticketNumber);

        if (foundTicket != null) {
            System.out.println("\n=== Ticket Details ===");
            System.out.println("Seat Number: " + foundTicket.getSeatNumber());
            System.out.println("Ticket Number: " + foundTicket.getTicketNumber());
            System.out.println("Price: $" + foundTicket.getPrice());
            System.out.println("Available: " + (foundTicket.isIsAvailable() ? "Yes" : "No"));

            // Search for the related booking in BStack
            Booking relatedBooking = null;
            LLStack<Booking> tempStack = new LLStack<>();

            while (!BStack.isEmpty()) {
                Booking booking = BStack.pop();
                for (Ticket bookedTicket : booking.getTicket()) {
                    if (bookedTicket != null && bookedTicket.getTicketNumber().equals(ticketNumber)) {
                        relatedBooking = booking;
                        break;
                    }
                }
                tempStack.push(booking);
                if (relatedBooking != null) {
                    break;
                }
            }

            // Restore BStack to its original state
            while (!tempStack.isEmpty()) {
                BStack.push(tempStack.pop());
            }

            // Display related booking if found
            if (relatedBooking != null) {
                displayBookingDetails(relatedBooking);
            } else {
                System.out.println("Related booking not found.");
            }
        } else {
            System.out.println("\u001B[41m" + "Ticket with ticket number " + ticketNumber + " was not found.");
        }
    }

    // 6. View the most booked event and prompt for reservation
    public static void viewMostBookedEvent() {
        Booking mostBookedEvent = BStack.findMostBookedEvent(); // Finds the most booked event

        if (mostBookedEvent != null) {
            Event associatedEvent = mostBookedEvent.getEvent();

            if (associatedEvent != null) {

                System.out.println("\n=== Most Booked Event ===");
                System.out.println("Event Name: " + associatedEvent.getEvent_Name());

                if (associatedEvent instanceof Movie) {
                    Movie movieEvent = (Movie) associatedEvent; // Cast to Movie
                    System.out.println("Movie Title: " + movieEvent.getTitle());
                }

                System.out.println("Location: " + associatedEvent.getLocation());
                System.out.println("Date: " + associatedEvent.getEventDate());

                // Ask the user if they want to book this event
                System.out.print("Would you like to book this event? (yes/no): ");
                char response = in.next().charAt(0);

                switch (response) {
                    case 'Y':
                    case 'y':
                        reserveBooking(); // Call the reserveBooking method
                        break;
                    default:
                        System.out.println("Returning to the main menu.");
                }

            } else {
                System.out.println("\u001B[41m" + "No event associated with the most booked booking.");
            }
        } else {
            System.out.println("\u001B[41m" + "No events found in booking history.");
        }
    }

    // 7. View, Modify, Remove customer
    public static void modifyCustomer() {

        System.out.print("Enter Customer ID: ");
        int custID = in.nextInt();

        Customer cust = custList.Sreach(custID);

        if (cust == null) {
            // If the customer doesn't exist, return immediately
            return;
        }

        System.out.println("\n=== Customer Information ===");
        System.out.println("Full Name: " + cust.getFullName());
        System.out.println("ID: " + cust.getID());
        System.out.println("Age: " + cust.getAge());
        System.out.println("Gender: " + cust.getGender());
        System.out.println("Email: " + cust.getEmail());
        System.out.println("Phone Number: " + cust.getPhoneNumber());

        System.out.println("Do you want to modify/remove? (Y/N) ");
        char answer = in.next().charAt(0);

        if (answer == 'Y' || answer == 'y') {

            // Prompt the user for modification options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Edit Customer Information");
            System.out.println("2. Remove Customer");
            System.out.println("0. Cancel");
            System.out.print("Enter your choice: ");

            int choice = in.nextInt();
            in.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Edit customer information
                    System.out.println("\n=== Edit Customer Information ===");

                    // Allow the user to modify each field if desired
                    System.out.print("Enter new Full Name (or press Enter to keep current): ");
                    String fullName = in.nextLine();
                    if (!fullName.isEmpty()) {
                        cust.setFullName(fullName);
                    }

                    System.out.print("Enter new Age (or press Enter to keep current): ");
                    String ageInput = in.nextLine();
                    if (!ageInput.isEmpty()) {
                        cust.setAge(Integer.parseInt(ageInput));
                    }

                    System.out.print("Enter new Gender (or press Enter to keep current): ");
                    String gender = in.nextLine();
                    if (!gender.isEmpty()) {
                        cust.setGender(gender);
                    }

                    System.out.print("Enter new Email (or press Enter to keep current): ");
                    String email = in.nextLine();
                    if (!email.isEmpty()) {
                        cust.setEmail(email);
                    }

                    System.out.print("Enter new Phone Number (or press Enter to keep current): ");
                    String phoneNumber = in.nextLine();
                    if (!phoneNumber.isEmpty()) {
                        cust.setPhoneNumber(phoneNumber);
                    }

                    System.out.println("\u001B[44m" + "Customer information updated successfully!");
                    break;

                case 2: // Remove customer
                    custList.removeCustomer(custID);
                    break;

                case 0: // Cancel
                    System.out.println("Operation cancelled.");
                    break;

                default:
                    System.out.println("\u001B[41m" + "Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static Ticket[] generateTicket(int count, double price) {
        Ticket[] t = new Ticket[count];
        for (int i = 0; i < count; i++) {
            t[i] = new Ticket(i + 1, generateTicketNumber(), price, true);
            TQueue.enqueue(t[i]);
        }
        return t;
    }

    private static void AvailableEvent() {
        if (eventList.isEmpty()) {
            Ticket[] movie = generateTicket(7, 60);
            Ticket[] sport = generateTicket(5, 75);

            // Adding movie events to the list
            Event busyBee = new Movie("Busy Bee", "Sci-Fi", 120, "Movie", 1003, "Cinema City", true, movie,
                    LocalDate.now().plusDays(1));
            Event manifest = new Movie("Manifest", "Horror", 168, "Movie", 1004, "Grand Theater", true, movie,
                    LocalDate.now().plusDays(2));
            Event basketball = new Sports("Basketball", "Lakers vs Mavericks", "Sport", 3007, "PNU Stadium", true, 
                    sport, LocalDate.now().plusDays(3));
            Event football = new Sports("Football", "Eagles vs Colts", "Sport", 3009, "UBS Arena", true, sport, 
                    LocalDate.now().plusDays(4));

            eventList.add(busyBee);
            eventList.add(manifest);
            eventList.add(basketball);
            eventList.add(football);

            // Create fake bookings to show on most booked event
            Customer fakeCustomer = new Customer("", 0, 0, "", "@example.com", "000000");

            Ticket[] reservedTickets = {movie[0], movie[1]};
            for (Ticket ticket : reservedTickets) {
                ticket.setIsAvailable(false); // Mark as unavailable
                ticket.setTicketNumber(generateTicketNumber());
            }

            Booking fakeBooking = new Booking(Booking.getNum(), fakeCustomer, reservedTickets, true, "Confirmed", manifest);
            BStack.push(fakeBooking);
        }
    }

    private static String generateTicketNumber() {
        Random rand = new Random();
        StringBuilder ticketNumber = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            ticketNumber.append(rand.nextInt(10)); // Append a random digit
        }
        return ticketNumber.toString();
    }

    private static void displayBookingDetails(Booking booking) {
        if (booking != null) {
            // Display detailed booking information
            System.out.println("\n=== Booking Information ===");
            System.out.println("Booking ID: " + booking.getBooking_ID());
            System.out.println("Customer Name: " + booking.getCustomer().getFullName());
            System.out.println("Customer ID: " + booking.getCustomer().getID());
            System.out.println("Status: " + booking.getStatus());
            System.out.println("Total Price: $" + booking.calcTotal());

            // Display ticket information for this booking
            System.out.println("\n=== Ticket Information ===");
            Ticket[] tickets = booking.getTicket();
            if (tickets != null && tickets.length > 0) {
                boolean hasValidTickets = false;
                for (Ticket ticket : tickets) {
                    if (ticket != null) { // Ensure we only process non-null tickets
                        hasValidTickets = true;
                        System.out.println("Seat Number: " + ticket.getSeatNumber()
                                + ", Ticket Number: " + ticket.getTicketNumber()
                                + ", Price: $" + ticket.getPrice()
                                + ", Available: " + (ticket.isIsAvailable() ? "Yes" : "No"));
                    }
                }
                if (!hasValidTickets) {
                    System.out.println("No valid tickets found for this booking.");
                }
            } else {
                System.out.println("No tickets found for this booking.");
            }
        } else {
            System.out.println("\u001B[41m" + "Booking not found.");
        }
    }

}
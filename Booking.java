
package Group4_3w7;

import java.util.Arrays;

public class Booking implements Bookingable{
    private static int num = 1;
    private final int Booking_ID ;
    private Customer customer; 
    private Ticket ticket[];
    private boolean available;
    private String status;
    private Event event;

    public Booking(int Booking_ID, Customer customer, Ticket[] ticket, boolean available, String status, Event event) {
        this.Booking_ID = num++;
        this.customer = customer;
        this.ticket = ticket;
        this.available = true;
        this.status = status ;
        this.event = event; // Assign event
    }

    public int getBooking_ID() {
        return Booking_ID;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Booking.num = num;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ticket[] getTicket() {
        return ticket;
    }

    public void setTicket(Ticket[] ticket) {
        this.ticket = ticket;
    }
    
    public Event getEvent() { 
        return event;
    }

    public void setEvent(Event event) { 
        this.event = event;
    }
    
     @Override
    public void Booking() {
        setStatus("Confirmed");
        System.out.println("booking has been confirmed.");
    }

    public final void cancel() {
        setStatus("Cancelled");
        System.out.println("Booking cancelled successfully.");
    }

    @Override
    public double calcTotal() {
        double total = 0;
    for (Ticket ro : ticket) {
        if (ro != null) { // Skip null tickets to avoid NullPointerException
            total += ro.getPrice();
        }
    }
    total += total * Bookingable.TAX; 
    return total;
    }

    @Override
    public String toString() {
        return "\nBooking_ID: " + Booking_ID 
                + customer 
                + Arrays.toString(ticket)
                + "\nEvent: " + event.getEvent_Name() + " at " + event.getLocation() + " on " + event.getEventDate() 
                + "\nstatus: " + status ;
    }

    
    
}



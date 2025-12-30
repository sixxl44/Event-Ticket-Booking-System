
package Group4_3w7;

import java.time.LocalDateTime;

public class Ticket {
    private int seatNumber;
    private String ticketNumber;
    private double price;
    private boolean isAvailable;
    private LocalDateTime creationTime; // Added field for ticket creation time


    public Ticket(int seatNumber, String ticketNumber, double price, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.ticketNumber = ticketNumber;
        this.price = price;
        this.isAvailable = isAvailable;
        this.creationTime = LocalDateTime.now(); // Sets creation time to current time
    }

    
    
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    
    @Override
    public String toString() {
        return "Ticket: "
                +"seatNumber: " + seatNumber
                + "\nprice: " + price 
                + "\nisAvailable: " + isAvailable 
                + "\ncreationTime: " + creationTime ;
    }
   
}

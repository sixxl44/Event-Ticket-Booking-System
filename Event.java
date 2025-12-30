
package Group4_3w7;

import java.time.LocalDate;
import java.util.Arrays;

public abstract class Event {
 private String Event_Name;
 private int id;
 private String Location;
 private boolean available;
 private Ticket ticket[];
 LocalDate eventDate; // field for event date

    public Event(String Event_Name, int id, String Location, boolean available, Ticket[] ticket, LocalDate eventDate) {
        this.Event_Name = Event_Name;
        this.id = id;
        this.Location = Location;
        this.available = true;
        this.ticket = ticket;
        this.eventDate = eventDate;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String Event_Name) {
        this.Event_Name = Event_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Ticket[] getTicket() {
        return ticket;
    }

    public void setTicket(Ticket[] ticket) {
        this.ticket = ticket;
    }
    
    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

  public final int generateld() {

        int min = 1;
        int max = 1000;
        int id = (int) (Math.random() * ((max - min) + 1)) + min;
        return id;

    }
    @Override
    public String toString() {
        return  "Event Name: " + Event_Name
                + ", id: " + id 
                + ", Location: " + Location 
                + ", available: " + available 
                + ", Date: " + eventDate 
                + ", ticket: " + Arrays.toString(ticket) ;
    }


}




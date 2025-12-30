
package Group4_3w7;

import java.time.LocalDate;

public class Movie extends Event {
 private String Title;
 private String Genre;
 private int Duration;

    public Movie(String Title, String Genre, int Duration, String Event_Name, int id, String Location, boolean available, Ticket[] ticket,LocalDate eventDate) {
        super(Event_Name, id, Location, available, ticket, eventDate);
        this.Title = Title;
        this.Genre = Genre;
        this.Duration = Duration;
    }

    public Movie(String Event_Name, int id, String Location, boolean available, Ticket[] ticket, LocalDate eventDate) {
        super(Event_Name, id, Location, available, ticket, eventDate);
    }

    
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    @Override
    public String toString() {
        return "Moive Title: " + Title 
                + ", Genre: " + Genre 
                + ", Duration: " + Duration
                + "mins, Location: " + getLocation() +
               ", Date: " + getEventDate();
    }


}

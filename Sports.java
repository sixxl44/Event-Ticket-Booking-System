
package Group4_3w7;

import java.time.LocalDate;

public class Sports extends Event{
 private String Sport_Type;
 private String NameOfTeams;

    public Sports(String Sport_Type, String NameOfTeams, String Event_Name, int id, String Location, boolean available, Ticket[] ticket,LocalDate eventDate) {
        super(Event_Name, id, Location, available, ticket, eventDate);
        this.Sport_Type = Sport_Type;
        this.NameOfTeams = NameOfTeams;
    }

    public String getSport_Type() {
        return Sport_Type;
    }

    public void setSport_Type(String Sport_Type) {
        this.Sport_Type = Sport_Type;
    }

    public String getNameOfTeams() {
        return NameOfTeams;
    }

    public void setNameOfTeams(String NameOfTeams) {
        this.NameOfTeams = NameOfTeams;
    }

    @Override
    public String toString() {
        return "Sport Type: " + Sport_Type 
                + ", Name Of Teams: " + NameOfTeams
                + " Location: " + getLocation() 
                + ", Date: " + getEventDate();
    
    }
 
}


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
 * Class TicketHash:
 * 
 * This class is a HashMap to hold the ticket objects by their id
 * 
 * Each entry in the HashMap has a key (Ticket ID) and Object (Ticket)
 * HashMap<Integer, Ticket>
 * 
 */
public class TicketHash {
  // hash map for storing the tickets by ID
  public HashMap<Integer, Ticket> tickets;

  public TicketHash() {
    tickets = new HashMap<Integer, Ticket>();
  }

  // Add tickets to the HashMap by ID
  public void addTicket(Integer id, Ticket ticket) {
    tickets.put(id, ticket);
  }
  
  public int getSize() {
    return tickets.size();
  }
  
  // Testing Method to check if HashMap is getting the correct values
  public void printMap() {
    for (int i = 1; i < tickets.size() + 1; ++i) {
      System.out.println(i + ": " + tickets.get(i).getSubject());
    }
  }
  
  // Prints pages of 25 tickets at a time for specified page num
  public void printPage(int pageNum) {
    
    int ticketStart = (((pageNum - 1) * 25) + 1);
    int ticketEnd = ((pageNum) * 25) + 1;
    
    // If the ticket end exceeds the last ticket set to the last ticket
    if (ticketEnd > getSize() + 1) {
      ticketEnd = getSize() + 1;
    }
      
    System.out.println("Page: " + pageNum);
    for(int i = ticketStart; i < ticketEnd; ++i) {
      System.out.println("Ticket " + i + ": " + tickets.get(i).getSubject());
    }
  }
  
  public void printTicket(int id) {
    if (!tickets.containsKey(id)) {
      System.out.print("Ticket " + id + "does not exist!");
    }
    else {
      System.out.println("-------------- Ticket " + id + " --------------");
      System.out.println();
      System.out.println("Requested By: " + tickets.get(id).getRequesterId());
      System.out.println("Created: " + tickets.get(id).getCreatedAt());
      System.out.println("Updated: " + tickets.get(id).getUpdatedAt());
      System.out.println("Status: " + tickets.get(id).getStatus());
      System.out.println("Has Incidents: " + tickets.get(id).getHasIncidents());
      System.out.println("Description: " + tickets.get(id).getDescription());
      System.out.print("Tags: ");
      for (int i = 0; i < tickets.get(id).getTags().length; ++i) {
        System.out.print(tickets.get(id).getTags()[i]);
        
        if (i == tickets.get(id).getTags().length - 1) {
          System.out.println();
        }
        else {
          System.out.print(", ");
        }
      }
      System.out.println();
    }
  }
}

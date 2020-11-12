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
  
  // Testing Method to check if HashMap is getting the correct values
  public void printMap() {
    for (int i = 1; i < tickets.keySet().size() + 1; ++i) {
      System.out.println(i + ": " + tickets.get(i).getSubject());
    }
  }
}

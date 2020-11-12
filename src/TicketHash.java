import java.util.HashMap;

public class TicketHash {
  // hash map for storing the tickets by ID
  public HashMap<Integer, Ticket> tickets; 
  
  public TicketHash()
  {
    tickets = new HashMap<Integer, Ticket>();
  }
  
  public void addTicket(Integer id, Ticket ticket)
  {
    tickets.put(id, ticket);
  }
}

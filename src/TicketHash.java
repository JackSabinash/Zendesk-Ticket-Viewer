import java.util.HashMap;

/*
 * Class TicketHash:
 * 
 * This class is a HashMap to hold the ticket objects by their id
 * 
 * Each entry in the HashMap has a key (Ticket ID) and Object (Ticket) HashMap<Integer, Ticket>
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

  // return the size of the HashMap
  public int getSize() {
    return tickets.size();
  }


  // Prints pages of 25 tickets at a time for specified page num
  public void printPage(int pageNum) {

    int ticketStart = (((pageNum - 1) * 25) + 1);
    int ticketEnd = ((pageNum) * 25) + 1;
    
    System.out.println("Page: " + pageNum);
    int count = 1;
    for (Integer id : tickets.keySet()) {
      if ((count >= ticketStart) && (count < ticketEnd)) {
        System.out.println("Ticket " + id + ": " + tickets.get(id).getSubject());
      }
      ++count;
    }
  }

  /*
   * This method takes in a ticket id and prints out the extra information on about the ticket
   * 
   * Parameters: int id = the id of the page to print more information
   * 
   * returns: Nothing
   */
  public void printTicket(int id) {
    if (!tickets.containsKey(id)) {
      System.out.println("Ticket " + id + " does not exist!");
    } else {
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
        } else {
          System.out.print(", ");
        }
      }
      System.out.println();
    }
  }
}

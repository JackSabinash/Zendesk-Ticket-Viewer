
public class Test {

  public static void main(String[] args) {
    if (!testTicket()) {
      System.out.print("Ticket Object testing has failed");
    }
    else if (!testTicketHash()) {
      System.out.print("TicketHash Object testing has failed");
    }
    else {
      System.out.print("All tests have passed!");
    }
  }
  
  public static boolean testTicket() {
    Ticket ticket = new Ticket();
    
    // use setters 
    ticket.setCreatedAt("date");
    ticket.setUpdatedAt("date2");
    ticket.setSubject("subject");
    ticket.setDescription("description");
    ticket.setStatus("status");
    ticket.setRequesterId((long)1214224124);

    String[] tagArr = new String[3];
    for (int j = 0; j < tagArr.length; ++j) {
      
      tagArr[j] = "tag" + j;
    }
    ticket.setTags(tagArr);
    ticket.setHasIncidents(true);
    
    if (!ticket.getCreatedAt().equals("date")) {
      return false;
    }
    if (!ticket.getUpdatedAt().equals("date2")) {
      return false;
    }
    if (!ticket.getSubject().equals("subject")) {
      return false;
    }
    if (!ticket.getDescription().equals("description")) {
      return false;
    }
    if (!ticket.getStatus().equals("status")) {
      return false;
    }
    if (ticket.getRequesterId() != (long)1214224124) {
      return false;
    }
    for (int j = 0; j < ticket.getTags().length; ++j) {
      if (!ticket.getTags()[j].equals("tag" + j)) {
        return false;
      }
    }
    if (ticket.getHasIncidents() != true) {
      return false;
    }
    
    return true;
  }
  
  public static boolean testTicketHash() {
    TicketHash tickets = new TicketHash();
    
    Ticket ticket = new Ticket();
    
    // use setters 
    ticket.setCreatedAt("date");
    ticket.setUpdatedAt("date2");
    ticket.setSubject("subject");
    ticket.setDescription("description");
    ticket.setStatus("status");
    ticket.setRequesterId((long)1214224124);

    String[] tagArr = new String[3];
    for (int j = 0; j < tagArr.length; ++j) {
      
      tagArr[j] = "tag" + j;
    }
    ticket.setTags(tagArr);
    ticket.setHasIncidents(true);
    
    tickets.addTicket(1, ticket);
    
    if (tickets.getSize() != 1) {
      return false;
    }
    
    // check if prints are good.
//    tickets.printTicket(1);
//    tickets.printPage(1);
    
    
    return true;
  }
}
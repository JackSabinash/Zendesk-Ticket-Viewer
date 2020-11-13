import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class TicketViewer {

  public static void main(String[] args) {
    
    // String auth = "";

    // encode the string into base64 encoding
    // String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    
    // hard code for testing purposes
    String encodedAuth = "Basic anNhYmluYXNoQHdpc2MuZWR1Om1lU3JhbS1tb3poeTctYmVjbW96";
    String endPoint = "https://wisc3265.zendesk.com/api/v2/tickets.json";
    
    // Create the TicketHash for parser to store data
    TicketHash tickets = new TicketHash();

    JSONObject ticketJSON;
    String line;

    // Start on page 1 of tickets
    int page = 1;
    do {
      line = getData(page, encodedAuth, endPoint);
      ticketJSON = new JSONObject(line);
      parse(ticketJSON, tickets);
      ++page;

    } while (!ticketJSON.isNull("next_page"));

    tickets.printPage(1);
    tickets.printTicket(101);
  }

  /*
   * This method makes the http call using Zendesks API to get ticket data.
   * 
   * Parameters: 
   * int page = the page number of tickets 
   * 
   * returns: 
   * String returned by the API call
   */
  public static String getData(int page, String auth, String endPoint) {
    try {
      // URL of the Endpoint with get command and Authorization property
      URL url = new URL(endPoint + "?page=" + page);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Authorization", auth);

      // Open a reader for the incoming stream from the http connection
      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String line = br.readLine();
      return line;

    } catch (IOException e) {
      e.printStackTrace();

    }
    return null;
  }

  /*
   * This method parses the JSON object returned from Zendesk's API for tickets and stores them in
   * the TicketHash data type one ticket at a time creating a ticket object for each ticket.
   * 
   * Parameters: 
   * JOSNObject obj = JSON Object returned by the Zendesk API 
   * TicketHash ticket = TicketHash data type to store the tickets created.
   * 
   * returns: Nothing
   */
  public static void parse(JSONObject obj, TicketHash tickets) {
    // Get the JSON Array of tickets from the JSON object passed in
    JSONArray arr = obj.getJSONArray("tickets");

    // Parse the ticket json objects
    for (int i = 0; i < arr.length(); ++i) {
      Ticket ticket = new Ticket();
      
      // For each of the json objects in the array fill in the ticket data fields
      ticket.setCreatedAt(arr.getJSONObject(i).getString("created_at"));
      ticket.setUpdatedAt(arr.getJSONObject(i).getString("updated_at"));
      ticket.setSubject(arr.getJSONObject(i).getString("subject"));
      ticket.setDescription(arr.getJSONObject(i).getString("description"));
      ticket.setStatus(arr.getJSONObject(i).getString("status"));
      ticket.setRequesterId(arr.getJSONObject(i).getLong("requester_id"));

      JSONArray jTagArr = arr.getJSONObject(i).getJSONArray("tags");
      String[] tagArr = new String[jTagArr.length()];
      for (int j = 0; j < jTagArr.length(); ++j) {
        
        tagArr[j] = (String) jTagArr.getString(j);
      }
      ticket.setTags(tagArr);

      ticket.setHasIncidents(arr.getJSONObject(i).getBoolean("has_incidents"));
      
      // Get the id for the ticket that will be the Tickets HashMap identifier
      Integer id = arr.getJSONObject(i).getInt("id");
      
      // Add the ticket created to the TicketHash Structure
      tickets.addTicket(id, ticket);
    }
  }
}

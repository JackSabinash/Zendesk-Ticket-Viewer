import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Class TicketViewer:
 * 
 * This class is the TicketViewer driver class which handles running the main application.
 * 
 * This class sends the http request and parses the lines returned.
 * 
 */
public class TicketViewer {

  /*
   * This is the main application that runs.
   * 
   */
  public static void main(String[] args) {
    // Create the TicketHash for parser to store data
    TicketHash tickets = new TicketHash();
    JSONObject ticketJSON;
    String line;
    Scanner sc = new Scanner(System.in);

    // Authentication steps
    System.out.println("Welcome to Zendesk Ticket Viewer");
    System.out.println("--------------------------------------------------------\n");
    System.out.println("Please enter the Zendesk company Username:");
    String endPoint = sc.nextLine();
    endPoint = "https://" + endPoint + ".zendesk.com/api/v2/tickets.json";
    System.out.println("Please enter the email address:");
    String auth = sc.nextLine();
    System.out.println("Please enter the password:");
    auth = auth + ":" + sc.nextLine();
    String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());

    // Start on page 1 -> send http requests and parse until no more data.
    int page = 1;
    do {
      line = getData(page, encodedAuth, endPoint);
      
      // if line is null something went wrong -> exit.    
      if (line == null) {
        System.out.println("Thank you for using Zendesk Ticket Viewer!");
        sc.close();
        return;
      }
      
      ticketJSON = new JSONObject(line);
      parse(ticketJSON, tickets);
      ++page;

    } while (!ticketJSON.isNull("next_page"));

    // Input handling and menu for UI CLI
    int pageNum = 1;
    int maxPage = (tickets.getSize() / 25) + 1;
    tickets.printPage(pageNum);

    loop: while (true) {
      System.out.println("--------------------------------------------------------\n");
      System.out.println("Menu:");
      System.out.println("'c': Current Page\n'n': Next Page\n'b': Previous Page\nTicket ID (1-" + tickets.getSize() + ") for more info\n'e': Exit.");
      System.out.println("--------------------------------------------------------\n");

      if (sc.hasNextInt()) {
        int id = sc.nextInt();
        sc.nextLine();
        tickets.printTicket(id);
        continue;
      } else {
        String s = sc.nextLine();

        switch (s) {
          case "c": {
            tickets.printPage(pageNum);
            continue;
          }
          
          case "n": {
            pageNum++;
            if (pageNum > maxPage) {
              System.out
                  .println("You are on the last page, please type 'b' to see the previous page.");
              pageNum = maxPage;
            } else {
              tickets.printPage(pageNum);
            }
            continue;
          }

          case "b": {
            pageNum--;
            if (pageNum < 1) {
              System.out.println("You are on page 1, please type 'n' to see the next page.");
              pageNum = 1;
            } else {
              tickets.printPage(pageNum);
            }
            continue;
          }

          case "e": {
            System.out.println("Thank you for using Zendesk Ticket Viewer!");
            break loop;
          }

          default: {
            System.out.println("Inavalid Input: Please enter a valid input.");
            continue;
          }
        }
      }

    }
    sc.close();
  }

  /*
   * This method makes the http call using Zendesks API to get ticket data.
   * 
   * Parameters: int page = the page number of tickets
   * 
   * returns: String returned by the API call
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
      // Handle the API being unavailable or failed authenticaiton and exit the program.
      System.out.println(e.getMessage());
      System.out.println("Connection or authentication has failed. Please try again.");

    }
    return null;
  }

  /*
   * This method parses the JSON object returned from Zendesk's API for tickets and stores them in
   * the TicketHash data type one ticket at a time creating a ticket object for each ticket.
   * 
   * Parameters: 
   *    JOSNObject obj = JSON Object returned by the Zendesk API 
   *    TicketHash ticket = TicketHash data type to store the tickets created.
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

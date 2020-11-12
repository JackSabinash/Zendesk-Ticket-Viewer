import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class TicketViewer {

  public static void main(String[] args) {
    
    String line = getData();
    TicketHash tickets = parse(line);
    
  }
  
  public static String getData()
  {
    // curl https://wisc3265.zendesk.com/api/v2/tickets.json -u jsabinash@wisc.edu:meSram-mozhy7-becmoz
    try {
      //String auth = "";
      
      // encode the string into base64 encoding
      //String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
      
      // hard code for testing purposes
      String encodedAuth = "Basic anNhYmluYXNoQHdpc2MuZWR1Om1lU3JhbS1tb3poeTctYmVjbW96";
      
      // URL of the Endpoint
      URL url = new URL("https://wisc3265.zendesk.com/api/v2/tickets.json");
      
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Authorization", encodedAuth);

      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String line = br.readLine();
      System.out.print(line);
      return line;
       
    } catch (IOException e) {
      e.printStackTrace(); 
      
    }
    return null;
  }
  
  public static TicketHash parse(String line)
  {
    TicketHash tickets = new TicketHash();
    
    
    
    
    return tickets;  
  }
  
}

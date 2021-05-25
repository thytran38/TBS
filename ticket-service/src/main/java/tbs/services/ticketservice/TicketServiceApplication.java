package tbs.services.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class TicketServiceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TicketServiceApplication.class, args);
		String inline= "";
		URL url = new URL("http://localhost:8081/trips");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();
		if(responseCode != 200){
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		}else
		{

//			JsonParser jsonParser = new JsonParser();
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext())
			{
				inline+=sc.nextLine();
			}
			System.out.println("\n JSON data in string format");
			System.out.println(inline);
			sc.close();
			}
		}



}

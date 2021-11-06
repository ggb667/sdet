package com.client;

//import java.util.Arrays;
import java.util.Scanner;

// import com.google.gson.JsonElement;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;
import com.model.Country;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Client implements CommandLineRunner{
    
    private static Country callRestService(String var){
        RestTemplate restTemplate = new RestTemplate();
        // String response = restTemplate.getForObject("https://restcountries.com/v3.1/alpha/Pe?fields=capital", String.class);
        // final JsonElement jse = JsonParser.parseString(response);
        // final JsonObject json = jse.getAsJsonObject();
        // System.out.println("Response:" + response);
        // System.out.println("JSE:" + jse);
        // System.out.println("Json:" + json);
        // System.out.println("Is Capital An Array?:" + json.get("capital").isJsonArray());
        Country c = restTemplate.getForObject("https://restcountries.com/v3.1/alpha/" + var + "?fields=capital", Country.class);
        //System.out.println("Country: " + c);
        return c;
    }
	
    @Override
	public void run(String... args) throws Exception {  
        System.out.println("Enter a 2 or 3 character country code or country name (ex: Pe, Per or Peru): ");
        try(Scanner scanner = new Scanner(System.in)){
            String var;
            while (!"exit".equalsIgnoreCase(var = scanner.nextLine())) {
                System.out.println(callRestService(var));  
            } // <-- start a new iteration, while still in the same try block
        }
	}
}

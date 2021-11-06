package com.client;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.country_service.CountryServiceApplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;    
// import com.google.gson.JsonElement;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CountryServiceApplication.class, 
initializers = ConfigDataApplicationContextInitializer.class)
public class ClientTest {

    @Test
    public void tesGoodCalls() {
        Client client = new Client();
        //assertTrue(client==null);
        assertTrue("{\"capital\":[\"Lima\"]}".equals(client.callRestService("Pe")));
        assertTrue("{\"capital\":[\"Lima\"]}".equals(client.callRestService("Per")));
        assertTrue("[{\"capital\":[\"Lima\"]}]".equals(client.callRestService("Peru")));
    }

    @Test
    public void testBadCalls() {
        Client client = new Client();
        //assertTrue(client==null);
        assertTrue("Client Error: Bad Request".equals(client.callRestService("")));
        assertTrue("No Value Found".equals(client.callRestService("Zip")));
        assertTrue("Not Found".equals(client.callRestService("NADA")));
        assertTrue("No Value Found".equals(client.callRestService("ZZ")));
    }

	// String response = restTemplate.getForObject("https://restcountries.com/v3.1/alpha/Pe?fields=capital", String.class);
	// final JsonElement jse = JsonParser.parseString(response);
	// final JsonObject json = jse.getAsJsonObject();
	// System.out.println("Response:" + response);
	// System.out.println("JSE:" + jse);
	// System.out.println("Json:" + json);
	// System.out.println("Is Capital An Array?:" + json.get("capital").isJsonArray());

}
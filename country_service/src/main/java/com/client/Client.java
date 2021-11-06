package com.client;

import java.util.Arrays;
import java.util.Scanner;
import com.client.error.RestTemplateResponseErrorHandler;
import com.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Profile("!test")
@Component
public class Client implements CommandLineRunner {
    
    public static boolean interactive = true;

    @Autowired
    private ApplicationContext context;

    public void close() {
        SpringApplication.exit(context);
    }

    public static Country callCodeRestService(String var){
        RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler()).build();
        String URL = "https://restcountries.com/v3.1/alpha/";
        Country c = restTemplate.getForObject(URL + var +"?fields=capital", Country.class);
        return c;
    }

    public static Country[] callNameRestService(String var){
        RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler()).build();
        String URL = "https://restcountries.com/v3.1/name/";
        Country[] ca = restTemplate.getForObject(URL + var +"?fields=capital", Country[].class);
        return ca;
    }

    @Override
	public void run(String... args) throws Exception {
        System.out.println("Enter a 2 or 3 character country code or country name (ex: 'Pe', 'Per' or 'Peru'), or 'exit' to stop: ");
        try(Scanner scanner = new Scanner(System.in)){
            String var;
            while (!"exit".equalsIgnoreCase(var = scanner.nextLine())) {
                callRestService(var);
            } // <-- start a new iteration, while still in the same try block
        }
        close();
	}

    String callRestService(String var) {
        String retval;
        try{
            if(var.length()<4){
                Country c = callCodeRestService(var);
                retval = c == null? "No Value Found": c.toString();//(bug in remote API), returns null
                System.out.println(retval);
            } else {
                System.out.println(retval = Arrays.deepToString( 
                   callNameRestService(var) ));
            }
        } catch(RuntimeException re) {
            System.err.println(retval = re.getMessage());
        }
        return retval;
    }

}


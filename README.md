# sdet test

To run the code navigate to the country_service directory and run the maven command:
.\mvnw spring-boot:run

To run the tests run:
mvn test

Notes on the implementatiuon:

There is a life cycle hiccup in the ClientTest that runs fine from the command line but that hits an initializzzation error in the IDE (spurious?).  Possibly related to skipping autowiring in

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CountryServiceApplication.class, 
initializers = ConfigDataApplicationContextInitializer.class)

Usually Id mock the actual http calls out so we wounldn't care if the internet was down, etc.

I considered making a web front end, but in the end I went with a CommandLineRunner that uses a scanner from Stdin.
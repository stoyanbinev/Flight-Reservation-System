import java.io.IOException;
import java.nio.file.*;
import java.util.function.Function;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Write a description of class FlightReader here.
 *
 * @A K M NAHARUL HAYAT
 * @1 (a version number or a date)
 */
public class FlightReader
{
    private static final int NUMBER_OF_FIELDS = 9;
    private static final int CODE = 0,ORIGIN = 1,DESTINATION = 2,DATE = 3,DEPARTURETIME = 4,ARRIVALTIME = 5,AIRLINE = 6,PRICE = 7,TYPE = 8;
    private List<Flights> flights;
    private String dataFileName = "Flights.csv";
    /**
     * Constructor for objects of class FlightReader
     */
    public FlightReader(String fileName)
    {
        // initialise instance variables
       flights = new ArrayList<Flights>();
       flights.addAll(getFlights(fileName));
    }
    public FlightReader()
    {
        // initialise instance variables
       flights = new ArrayList<Flights>();
       flights.addAll(getFlights(dataFileName));
    }
    public ArrayList<Flights> getFlights(String filename)
    {
        Function<String, Flights> createFlights = 
            record -> {
                           String[] parts = record.split(",");
                           if(parts.length == NUMBER_OF_FIELDS) {
                               try {
                                   String code = parts[CODE].trim();
                                   String origin = parts[ORIGIN].trim();
                                   String destination = parts[DESTINATION].trim();
                                   String date = parts[DATE].trim();
                                   String departureTime = parts[DEPARTURETIME].trim();
                                   String arrivalTime = parts[ARRIVALTIME].trim();
                                   String airline = parts[AIRLINE].trim();
                                   double price = Double.parseDouble(parts[PRICE].trim());
                                   String type = parts[TYPE].trim();
                                   return new Flights(code, origin, destination,date, departureTime, arrivalTime,airline,price,type);
                               }
                               catch(NumberFormatException e) {
                                   System.out.println("Flight record has a malformed integer: " + record);
                                   return null;
                               }
                           }
                           else {
                               System.out.println("Flight record has the wrong number of fields: " + record);
                               return null;
                           }
                       };
        ArrayList<Flights> flights;
        try {
            flights = Files.lines(Paths.get(filename)).filter(record -> record.length() > 0 && record.charAt(0) != '#').map(createFlights).filter(x -> x != null).collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            flights = new ArrayList<>();
        }
        return flights;
    }
    public void printAllRecord()
    {
        flights.forEach(flight -> System.out.println(flight.getDetails()));
    }
    public void searchFlights(String airportName, String date)
    {
        flights.stream().filter(flight -> (airportName.equals(flight.getOrigin())  && dateComparison(date,flight.getDate()))).forEach(flight -> System.out.println(flight.getDetails()));        
    }
    private boolean dateComparison(String dateInput,String actualDate)
    {
        String[] literals = dateInput.split("");
        for(String x:literals)
        {
            if(actualDate.contains(x))
            {
                return true;
            }
        }
        return false;
    }
}

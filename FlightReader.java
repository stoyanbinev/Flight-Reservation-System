import java.io.IOException;
import java.nio.file.*;
import java.util.function.Function;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Handles reading flights from data file and flight operations.
 *
 * @author A K M NAHARUL HAYAT
 * @version  1.0
 */
public class FlightReader
{   
    /** How many fields there are in the CSV */
    private static final int NUMBER_OF_FIELDS = 9;
    /** Indexes of different column names */
    private static final int CODE = 0,ORIGIN = 1,DESTINATION = 2,DATE = 3,DEPARTURETIME = 4,ARRIVALTIME = 5,AIRLINE = 6,PRICE = 7,TYPE = 8;
    /** List of flights */
    private List<Flights> flights;
    /** Name of the CSV file*/
    private String dataFileName = "Flights.csv";
    /**
     * Constructor for objects of class FlightReader
     * @param fileName The name of the data file
     */
    public FlightReader(String fileName)
    {
        // initialise instance variables
       flights = new ArrayList<Flights>();
       flights.addAll(getFlights(fileName));
    }
    /** 
     *  Add entries to the list of flights
     */
    public FlightReader()
    {
        // initialise instance variables
       flights = new ArrayList<Flights>();
       flights.addAll(getFlights(dataFileName));
    }
    /** 
     *  Get flights from the file
     *  @param filename The name of the data file
     *  @return ArrayList   List of flights
     */
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
            flights = Files.lines(Paths.get(filename))      // Get file
                           .filter(record -> record.length() > 0 && record.charAt(0) != '#')        //  Check if line contains data
                           .map(createFlights)      // Create object
                           .filter(x -> x != null)      // Filter null objects
                           .collect(Collectors.toCollection(ArrayList::new));       // Initiate ArrayList with objects
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            flights = new ArrayList<>();
        }
           
        return flights;
    }
    /** 
     *  Prints all records from data file
     */
    public void printAllRecord()
    {
        flights.forEach(flight -> System.out.println(flight.getDetails()));
    }
    /** 
     *  Searches for a flight
     *  @param airport Name of the airport
     *  @param date The date of the searched item
     */
    public void searchFlights(String airportName, String date)
    {
        flights.stream()        // Initiate stream
               .filter(flight -> (airportName.equals(flight.getOrigin())  && dateComparison(date,flight.getDate())))        // Filter flights for conditions
               .forEach(flight -> System.out.println(flight.getDetails()));         // Print all that match required     
    }
    /** 
     *  Check if two dates have matching days
     *  @param  dateInput Date from input
     *  @param  acrualDate Date to compare with
     *  @return boolean Result of comparision
     */
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

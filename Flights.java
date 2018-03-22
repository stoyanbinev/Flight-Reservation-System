/**
 * Handles flight data.
 *
 * @author A K M NAHARUL HAYAT
 * @version  1.0
 */
public class Flights
{
    /** Data of the flight */
    private final String code;
    private final String origin;
    private final String destination;
    private final String date;
    private final String departureTime;
    private final String arrivalTime;
    private final String airline;
    private final double price;
    private final String type;

    /**
     * Constructor for objects of class Flights
     * @param all Data for a flight
     */
    public Flights(String code, String origin, String destination,String date, String departureTime, String arrivalTime, String airline, double price,String type)
    {
        // initialise instance variables
       this.code = code;
       this.origin = origin;
       this.destination = destination;
       this.date = date;
       this.departureTime = departureTime;
       this.arrivalTime = arrivalTime;
       this.airline = airline;
       this.price = price;
       this.type = type;
    }
    /** 
     *  Get the date of a flight
     *  @return     Date of the flight
     */
    public String getDate()
    {
        return date;
    }
    /** 
     *  Get origin of the flight
     *  @return     Origin of flight
     */
    public String getOrigin()
    {
        return origin;
    }
    /** 
     *  Get details of a flight
     *  @return     Deatils of the file as a string with description
     */
    public String getDetails() 
    {
        return "flight code = "  +code + 
               ", origin = " + origin + 
               ", destination = " + destination + 
               ", date = " + date +
               ", departure time = " + departureTime + 
               ", arrival time = " + arrivalTime + ", airline = " + airline + ", price = " + price + ", type = " + type;
    }

}

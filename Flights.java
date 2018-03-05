
public class Flights
{
    // instance variables - replace the example below with your own
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
    public String getDate()
    {
        return date;
    }
    public String getOrigin()
    {
        return origin;
    }
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

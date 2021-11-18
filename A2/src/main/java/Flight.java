
public class Flight
{

    private int flightNumber;
    private String airplaneType;
    private String originCity;
    private String destCity;
    private String originAirport;
    private String destAirport;
    private int totalSeats;
    private int remainingSeats;
    private float ticketPrice;


    //Default Constructor
    //Flight() {}

    //Parameterized constructor
    public Flight(int flightNumber, String airplaneType, String originCity, String destCity, String originAirport,
                  String destAirport, int totalSeats, int remainingSeats, float ticketPrice)
    {
        this.flightNumber = flightNumber;
        this.airplaneType = airplaneType;
        this.originCity = originCity;
        this.destCity = destCity;
        this.originAirport = originAirport;
        this.destAirport = destAirport;
        this.totalSeats = totalSeats;
        this.remainingSeats = remainingSeats;
        this.ticketPrice = ticketPrice;
    }

    //Display function
    public String displayFlightDetails()
    {
        return ("Flight Num : " + this.flightNumber
                +" Origin : " + this.originCity
                +" Destination : " + this.destCity);
    }


    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestAirport() {
        return destAirport;
    }

    public void setDestAirport(String destAirport) {
        this.destAirport = destAirport;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }



}

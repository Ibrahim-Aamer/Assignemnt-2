import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.DateFormat;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.StringTokenizer;
import java.util.ArrayList; // import the ArrayList class
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight
{
    private static int flightCounter = 0;
    private int flightNumber;
    private String airplaneType;
    private String originCity;
    private String destCity;
    private String originAirport;
    private String destAirport;
    private int totalSeats;
    private int remainingSeats;
    private float ticketPrice;
    private String strDate;
    private Date date;
    private ArrayList<PassengerTicket> passengerArray;


    //Default Constructor
    //Flight() {}

    //Parameterized constructor
    public Flight(String airplaneType, String originCity, String originAirport,String destCity,
                  String destAirport, int totalSeats, int remainingSeats, float ticketPrice, String date)
    {
        flightCounter++;//incrementing flightCounter at every new instance
        this.flightNumber = flightCounter;
        this.airplaneType = airplaneType;
        this.originCity = originCity;
        this.destCity = destCity;
        this.originAirport = originAirport;
        this.destAirport = destAirport;
        this.totalSeats = totalSeats;
        this.remainingSeats = remainingSeats;
        this.ticketPrice = ticketPrice;
        this.passengerArray = new ArrayList<PassengerTicket>();//passenger array to hold the flights passengers
        this.strDate = date;
        //parsing date string into date format
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.date = formatter.parse(date);
        }
        catch(Exception e) {
           //e.printStackTrace();
           System.out.println(e.getMessage());
        }
        //Read passenger data from file
    }

    public void TakeSeat()//called when a seat is booked/reserved
    {
        this.remainingSeats--;
    }

    public void AddSeat()//called when a seat is cancelled by user
    {
        this.remainingSeats++;
    }

     public String getFileFormatFlightDetails()
     {

         return (this.flightNumber + "," +
                 this.airplaneType + "," +
                 this.originCity + "," +
                 this.originAirport + "," +
                 this.destCity + "," +
                 this.destAirport + "," +
                 this.totalSeats + "," +
                 this.remainingSeats + "," +
                 this.ticketPrice + "," +
                 this.strDate
                 );
     }

    //Display function
    public String displayFlightDetails()
    {
        return (this.flightNumber +"         "+this.originAirport +"              " + this.destAirport+
                "         " +this.ticketPrice+"        "+this.remainingSeats);
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<PassengerTicket> getPassengerArray() {
        return passengerArray;
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

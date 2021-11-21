import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.StringTokenizer;
import java.util.ArrayList; // import the ArrayList class
import java.text.SimpleDateFormat;
import java.util.Date;


public class FlightManagementSystem {
    public static void main(String[] args)
    {

        System.out.println("hello world");
        User usr = new User();

        ArrayList<Flight> flightsArray = new ArrayList<Flight>();

        flightsArray = ReadFlightDetails();

        System.out.println("FLIGHT DETAILS :  \n");

        for(int c=0 ; c<flightsArray.size() ; c++)
        {
            System.out.println(flightsArray.get(c).getFileFormatFlightDetails());
            //System.out.println("Error here");
        }

        SaveFlightDetails(flightsArray);


    }

    //This function writes(saves) the updated details of flights into flights.csv
    public static void SaveFlightDetails(ArrayList<Flight> flights)
    {
        try {
            FileWriter myWriter = new FileWriter("A2/src/flights.csv");

            myWriter.write("FlightNumber,airplaneType,originCity,originAirport,destCity,destAirport,totalSeats,remainingSeats,ticketPrice\n");

            //writing all flights details with for loop
            for(int c=0 ; c< flights.size() ; c++) {
                myWriter.write(flights.get(c).getFileFormatFlightDetails()+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred while opening the file.");
            e.printStackTrace();
        }

    }

    //Function to read all flight details from flight.csv
    public static ArrayList<Flight> ReadFlightDetails()
    {
        System.out.println("Flight details reading function....");

        ArrayList<Flight> flights = new ArrayList<Flight>();//defining arraylist if flights

        try {
            File myObj = new File("A2/src/flights.csv");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();


                StringTokenizer st = new StringTokenizer(data,",");

                //System.out.println(st.nextToken()+","+st.nextToken()+","+st.nextToken()+","+st.nextToken()
                //        +","+st.nextToken()+","+st.nextToken()+","+st.nextToken()+","+st.nextToken()+","+st.nextToken() +","+st.nextToken());

                st.nextToken();//avoiding FlightNumber token
                //Creating new temp flight object from tokens recieved from file
                Flight temp = new Flight(st.nextToken(),st.nextToken(),st.nextToken(),
                        st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),Float.parseFloat(st.nextToken()),st.nextToken());
                //Adding temp flight object into ArrayList
                flights.add(temp);

                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return flights;


    }
}
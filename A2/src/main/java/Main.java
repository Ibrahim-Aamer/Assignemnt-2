import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.StringTokenizer;
import java.util.ArrayList; // import the ArrayList class

public class Main {
    public static void main(String[] args)
    {

        System.out.println("hello world");
        User usr = new User();

        readFlightDetails();

    }

    //Function to read all flight details from flight.csv
    public static void readFlightDetails()
    {
        System.out.println("Flight details reading function....");

        ArrayList<Flight> flights = new ArrayList<Flight>();//arraylist if flights

        try {
            File myObj = new File("A2/src/flights.csv");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();

                StringTokenizer st = new StringTokenizer(data,",");
                //st.hasMoreTokens();
                //System.out.println(st.nextToken()+st.nextToken());
                Flight temp = new Flight(Integer.parseInt(st.nextToken()),st.nextToken(),st.nextToken(),st.nextToken(),
                        st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),Float.parseFloat(st.nextToken()));
                flights.add(temp);

                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        System.out.println("FLIGHT DETAILS :  \n");

        for(int c=0 ; c<flights.size() ; c++)
        {
            System.out.println(flights.get(c).displayFlightDetails());
        }

    }
}
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.StringTokenizer;
import java.util.ArrayList; // import the ArrayList class
import java.text.SimpleDateFormat;
import java.util.Date;


public class FlightManagementSystem
{


    //Main implements the menu of the whole flight management system
    public static void main(String[] args)
    {


        //Array lists for users,flights and passenger tickets
        ArrayList<User> users;
        ArrayList<Flight> flights;
        ArrayList<PassengerTicket> passengerTickets;

        //Initialize Flight Management System...
        //Reading all files....
        flights = ReadFlightDetails();//Reading flights.csv and saving in flights arraylist
        users = ReadUsersDetails();//Reading users.csv and saving in users arraylist
        //Reading passengerTickets.csv and saving in passengerTickets arraylist
        passengerTickets = ReadPassengerTicketDetails(flights,users);

       /* User temp = new User("ibrahim","aamer","house 61");
        try {
            if(false) {//checking if exection will be thrown
                throw new UnderageUserException("Hello");
            }
        }
        catch(UnderageUserException e)
        {
            System.out.println("Hello exception");
            e.getMessage();
        }
        //users.add(temp);

        PassengerTicket tempP = new PassengerTicket("ibrahim","male",21,"house 61","1234",flights.get(1),users.get(1),"Reserved");
        passengerTickets.add(tempP);*/

        System.out.println("-----------------------------------\n"
                          +"WELCOME TO FLIGHT MANAGEMENT SYSTEM\n"
                          +"-----------------------------------");


        System.out.println("=================\n"
                          +"     MENU        \n"
                          +"-----------------\n"
                          +"1. Login\n"
                          +"2. Register\n"
                          +"3. Exit\n"
                          +"=================");

        System.out.println("Enter Choice : ");
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String choice = scanInput.nextLine();  // Read user input

        //Input validation loop
        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3"))
        {
            System.out.println("Invalid Choice!\n");
            System.out.println("Enter Choice : ");
            choice = scanInput.nextLine();  // Read user input
        }

        User currentUser;//This user objects holds user of current session

        if( choice.equals("1") )//Login
        {
            //UserLogin function throws a custom exception of UserNameNotFound
            try {
                currentUser = UserLogin(users);
                //Now that currentUser variable has been initialized we will call passengerticket function
                passengerTickets.addAll(TicketMenuFunction(currentUser,flights));
            }
            catch (UsernameNotFoundException e) {
                System.out.println(e.getMessage());
            }


        }
        else if( choice.equals("2") )//Creating new user
        {
            currentUser = UserRegister();//creating new user

            //Adding newly made user to users arraylist
            users.add(currentUser);
            //System.out.println(currentUser.getFileFormatUserDetails());

            //Now that currentUser variable has been initialized we will call passengerticket function
            passengerTickets.addAll(TicketMenuFunction(currentUser,flights));

        }





       /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));
        if(date.before(flights.get(1).getDate())) {
            // In between
            System.out.println("Date valid");
        }
        else
        {
            System.out.println("Date not valid");
        }*/

        SaveFlightDetails(flights);
        SaveUserDetails(users);
        SavePassengerTicketsDetails(passengerTickets);


    }

    //This function takes current user and flights array list as parameters
    // and books/reserves passengerTicket for user
    public static ArrayList<PassengerTicket> TicketMenuFunction(User currentUser,ArrayList<Flight> flights)
    {
        System.out.println("=======================\n"
                          +"      TICKET MENU      \n"
                          +"-----------------------\n"
                          +"1. Reserve Ticket      \n"
                          +"2. Book Ticket         \n"
                          +"3  Book Reserved Ticket\n"
                          +"4. Exit\n"
                          +"=======================");

        System.out.println("Enter Choice : ");
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String choice = scanInput.nextLine();  // Read user input

        //Input validation loop
        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
        {
            System.out.println("Invalid Choice!\n");
            System.out.println("Enter Choice : ");
            choice = scanInput.nextLine();  // Read user input
        }

        //Creating a new passengerTickets Arraylist
        ArrayList<PassengerTicket> passengerTickets = new ArrayList<PassengerTicket>();

        if(choice.equals("1"))//reserve ticket
        {
            try {
                passengerTickets.addAll(TicketSearchFunction(currentUser, flights, "Reserved"));
            }
            catch(InvalidDateException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else if(choice.equals("2"))//book ticket
        {
            try {
                passengerTickets.addAll(TicketSearchFunction(currentUser, flights, "Booked"));
            }
            catch(InvalidDateException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else if(choice.equals("3"))//book previously reserved tickets
        {

        }

        return passengerTickets;
    }

    //This function books/resererves a ticket for user after providing the search result
    //it first takes flight details from user which are to be searched
    public static ArrayList<PassengerTicket> TicketSearchFunction(User currentUser,ArrayList<Flight> flights,String bookStatus)
    throws InvalidDateException
    {
        System.out.println("====================================\n"+
                           "        SEARCH FOR FLIGHT           \n"
                         + "------------------------------------");
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String origin;
        System.out.print("Enter Origin City : ");
        origin = scanInput.nextLine();  // Read firstname from user input
        String dest;
        System.out.print("Enter Destination City : ");
        dest = scanInput.nextLine();  // Read lastname from user input
        Date flightDate;

        //input validation for date input
        while(true) {
            System.out.print("Enter date (format dd/MM/yyyy) : ");
            String date = scanInput.nextLine();  // Read address from user input

            //parsing date string into date format
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                flightDate = formatter.parse(date);

                break;//loop breaks after date accepted
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();//todays date
        System.out.println(formatter.format(date));
        if(date.before(flightDate)) {
            // In between
            //System.out.println("Date valid");
        }
        else
        {
            System.out.println("Date not valid");
            throw new InvalidDateException("InvalidDateException. Date not valid ");
        }


        System.out.println("___________________________________________________________");
        System.out.println("                  AVAILABLE DIRECT FLIGHTS                 ");
        System.out.println("-----------------------------------------------------------");
        //Now we will search for flights at and after the given date
        //Loop to iterate over flights array list and display result flights
        System.out.println("Flight#" +"--"+ "OriginAirport" +"--"
                + "DestAirport"+"--"+"TicketPrice"+"--"+"SeatsLeft");
        for(int c=0 ; c< flights.size() ; c++)
        {
            //matching origin city and dest city
            if(flights.get(c).getOriginCity().equals(origin) && flights.get(c).getDestCity().equals(dest))
            {
                //if the flight is after the given date
                if(flightDate.before(flights.get(c).getDate()))
                {
                    System.out.println(flights.get(c).displayFlightDetails());
                }
            }
        }

        System.out.println("___________________________________________________________");
        System.out.println("                 AVAILABLE INDIRECT FLIGHTS                ");
        System.out.println("-----------------------------------------------------------");

        int indirectCount = 0;

        //2 for loops to find indirect flights
        for(int c=0 ; c< flights.size() ; c++)
        {
            for(int i=0; i<flights.size();i++) {

                //matching origin city and dest city
                if (flights.get(c).getOriginCity().equals(origin) && flights.get(c).getDestCity().equals(flights.get(i).getOriginCity())
                        && flights.get(i).getDestCity().equals(dest)) {
                    //if the flight is after the given date
                    if (flightDate.before(flights.get(c).getDate())
                     && flights.get(c).getDate().before(flights.get(i).getDate()))
                    {
                        indirectCount++;
                        System.out.println("IN-DIRECT FLIGHT #" + Integer.toString(indirectCount));
                        System.out.println("Flight#" +"--"+ "OriginAirport" +"--"+ "DestAirport"+"--"+"TicketPrice"+"--"+"SeatsLeft");
                        System.out.println(flights.get(c).displayFlightDetails());
                        System.out.println(flights.get(i).displayFlightDetails());

                    }
                }
            }
        }

        System.out.println("___________________________________________________________");

        //Array list for passengerTickets
        ArrayList<PassengerTicket> passengerTickets = new ArrayList<PassengerTicket>();

        //Now booking/reserving passengerTickets
        System.out.println("=======================\n"
                          +"      BOOK FLIGHT      \n"
                          +"-----------------------\n"
                          +"1. Direct Flight       \n"
                          +"2. In-direct Flight    \n"
                          +"=======================");

        System.out.println("Enter Choice : ");
        String choice = scanInput.nextLine();  // Read user input

        //Input validation loop
        while(!choice.equals("1") && !choice.equals("2") )
        {
            System.out.println("Invalid Choice!\n");
            System.out.println("Enter Choice : ");
            choice = scanInput.nextLine();  // Read user input
        }

        if(choice.equals("1"))//direct flight
        {
            int flightNum;
            System.out.print("Enter Direct Flight # : ");
            flightNum = scanInput.nextInt();  // Read address from user input

            passengerTickets.add(CreatePassengerTicket(currentUser,bookStatus,getFlightFromFlightNum(flights,flightNum)));

        }
        else if(choice.equals("2"))//indirect flight
        {
            int flight1Num;
            System.out.print("Enter Indirect Flight 1 # : ");
            flight1Num = scanInput.nextInt();  // Read address from user input
            int flight2Num;
            System.out.print("Enter Indirect Flight 2 # : ");
            flight2Num = scanInput.nextInt();  // Read address from user input

            passengerTickets.add(CreatePassengerTicket(currentUser,bookStatus,getFlightFromFlightNum(flights,flight1Num)));
            passengerTickets.add(CreatePassengerTicket(currentUser,bookStatus,getFlightFromFlightNum(flights,flight2Num)));


        }


        return passengerTickets;//return passengerTickets arraylist
    }

    public static PassengerTicket CreatePassengerTicket(User currentUser, String bookStatus,Flight flight)
    {
        System.out.println("====================================\n"+
                           "KINDLY FILL OUT REGISTRATION DETAILS\n"
                         + "------------------------------------");
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String name;
        System.out.print("Enter Name : ");
        name = scanInput.nextLine();  // Read firstname from user input
        String gender;
        System.out.print("Enter Gender : ");
        gender = scanInput.nextLine();  // Read lastname from user input
        String age;
        System.out.print("Enter Age : ");
        age = scanInput.nextLine();  // Read address from user input
        String passNum;
        System.out.print("Enter Passport Number : ");
        passNum = scanInput.nextLine();  // Read lastname from user input


        PassengerTicket passengerTicket = new PassengerTicket(name,gender,Integer.valueOf(age), currentUser.getAddress()
                , passNum,flight,currentUser,bookStatus);

        flight.TakeSeat();//decrementing remaining seat in flight

        System.out.println("------------------------------------");

        return passengerTicket;
    }

    //Registering a user and returning its object
    public static User UserRegister()
    {
        User user;//user object to return
        System.out.println("====================================\n"+
                           "KINDLY FILL OUT REGISTRATION DETAILS\n"
                         + "------------------------------------");
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String fName;
        System.out.print("Enter First Name : ");
        fName = scanInput.nextLine();  // Read firstname from user input
        String lName;
        System.out.print("Enter Last Name : ");
        lName = scanInput.nextLine();  // Read lastname from user input
        String address;
        System.out.print("Enter Address : ");
        address = scanInput.nextLine();  // Read address from user input


        //Parameterzied constructor2 for user
        User temp = new User(fName, lName,address);//creating new user

        System.out.println("====================================\n");

        return temp;
    }

    //This function takes a username and login the user and returns user object
    public static User UserLogin(ArrayList<User> usersArray) throws UsernameNotFoundException
    {
        User user;//user object to return

        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String enteredUserName;
        System.out.println("Enter Username : ");
        enteredUserName = scanInput.nextLine();  // Read username from user input

        //Loop to iterate over users array
        for(int c=0 ; c< usersArray.size(); c++)
        {
            //If username matched with a user return that object
            if(enteredUserName.equals(usersArray.get(c).getUserName()))
            {
                return usersArray.get(c);//return user
            }
        }

        throw new UsernameNotFoundException("UsernameNotFoundException: Invalid_Username.");
        //exception throws if code reaches here

        //return null;

    }


    public static ArrayList<User> ReadUsersDetails()
    {
        ArrayList<User> users = new ArrayList<User>();//creating new array list of users

        try {
            File myObj = new File("A2/src/users.csv");
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
                User temp = new User(st.nextToken(),st.nextToken(),st.nextToken(), st.nextToken());
                //Adding temp flight object into ArrayList
                users.add(temp);

                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error. Flights.csv Not Found");
            e.printStackTrace();
        }


        return users;
    }

    public static ArrayList<PassengerTicket> ReadPassengerTicketDetails(ArrayList<Flight> flights, ArrayList<User> users)
    {
        ArrayList<PassengerTicket> passengerTickets = new ArrayList<PassengerTicket>();
        //creating new array list of passengerTickets

        try {
            File myObj = new File("A2/src/passengerTickets.csv");
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
                PassengerTicket temp = new PassengerTicket(st.nextToken(),st.nextToken()
                        ,Integer.parseInt(st.nextToken()),st.nextToken(),st.nextToken(),
                        getFlightFromFlightNum(flights,Integer.parseInt(st.nextToken()))
                        ,getUserFromUserID(users,Integer.parseInt(st.nextToken()))
                ,st.nextToken());
                //Adding temp flight object into ArrayList
                passengerTickets.add(temp);

                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error. Flights.csv Not Found");
            e.printStackTrace();
        }


        return passengerTickets;
    }

    public static Flight getFlightFromFlightNum(ArrayList<Flight> flights,int flightNum)
    {
        for(int c=0 ; c<flights.size();c++)
        {
            if(flights.get(c).getFlightNumber() == flightNum)
            {
                return flights.get(c);
            }

        }
        System.out.println("getFlightFromFlightNum ERROR flight not found!");
        return null;
    }

    public static User getUserFromUserID(ArrayList<User> users,int userID)
    {
        for(int c=0 ; c<users.size();c++)
        {
            if(users.get(c).getUserID() == userID)
            {
                return users.get(c);
            }

        }
        System.out.println("getUserFromUserID ERROR user not found!");
        return null;
    }

    //This function writes(saves) the updated details of passengerTickets into passengerTickets.csv
    public static void SavePassengerTicketsDetails(ArrayList<PassengerTicket> passengerTickets)
    {
        try {
            FileWriter myWriter = new FileWriter("A2/src/passengerTickets.csv");

            myWriter.write("ID,Name,gender,age,address,passportNum,flightNum,bookedByUser,bookingStatus\n");

            //writing all user details with for loop
            for(int c=0 ; c< passengerTickets.size() ; c++)
            {
                myWriter.write(passengerTickets.get(c).getFileFormatPassengerTicketDetails()+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file(passengerTickets.csv).");

        } catch (IOException e) {
            System.out.println("An error occurred while opening the file.");
            e.printStackTrace();
        }

    }

    //This function writes(saves) the updated details of users into users.csv
    public static void SaveUserDetails(ArrayList<User> users)
    {
        try {
            FileWriter myWriter = new FileWriter("A2/src/users.csv");

            myWriter.write("userID,userName,firstName,lastName,address\n");

            //writing all user details with for loop
            for(int c=0 ; c< users.size() ; c++)
            {
                myWriter.write(users.get(c).getFileFormatUserDetails()+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file(user.csv).");

        } catch (IOException e) {
            System.out.println("An error occurred while opening the file.");
            e.printStackTrace();
        }

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
            System.out.println("Successfully wrote to the file(flights.csv).");

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
            System.out.println("Error. Flights.csv Not Found");
            e.printStackTrace();
        }


        return flights;


    }
}
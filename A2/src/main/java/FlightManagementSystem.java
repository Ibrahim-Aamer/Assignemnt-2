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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));

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
                UserLogin(users);
            }
            catch (UsernameNotFoundException e) {
                System.out.println(e.getMessage());
            }
            //Now that currentUser variable has been initialized we will call passengerticket function

        }
        else if( choice.equals("2") )//Creating new user
        {
            currentUser = UserRegister();//creating new user

            //Adding newly made user to users arraylist
            users.add(currentUser);
            //System.out.println(currentUser.getFileFormatUserDetails());

            //Now that currentUser variable has been initialized we will call passengerticket function


        }





       /* if(date.before(flights.get(1).getDate())) {
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
    public static ArrayList<PassengerTicket> PassengerTicketFunction(User currentUser,ArrayList<Flight> flights)
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

        if(choice.equals("1"))
        {

        }
        else if(choice.equals("2"))
        {

        }
        else if(choice.equals("3"))
        {

        }

        return passengerTickets;
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
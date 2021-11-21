import java.util.ArrayList; // import the ArrayList class

public class User
{
    private static int user_count;//Static variable... for user count
    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private String address;
    private ArrayList<PassengerTicket> bookedTickets;

    //Parameterized constructor1 only to be used in fileReading
    User(String userName,String firstName, String lastName, String address)
    {
        user_count++;
        this.userID = user_count; //every user will have a unique ID
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;

        //unique username for every user
        this.userName = userName;

        System.out.println("Username: " + this.userName +" Successfully Created");

    }

    //Parameterzied constructor2
    User(String firstName, String lastName, String address)
    {
        user_count++;
        this.userID = user_count; //every user will have a unique ID

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;

        //unique username for every user
        this.userName = this.firstName + this.lastName + Integer.toString(this.userID);

        System.out.println("Username: " + this.userName +" Successfully Created");
    }

    public String getFileFormatUserDetails()
    {
        return(Integer.toString(this.userID) +","+this.userName+","+this.firstName+","+this.lastName+","+this.address);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<PassengerTicket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(ArrayList<PassengerTicket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}

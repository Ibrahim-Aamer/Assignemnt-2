
public class PassengerTicket
{
    private static  int passengerCount = 0;
    private int ID;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String passportNum;
    private String bookStatus;//hold booking status
    private Flight flight;
    private User bookedBy;  //to hold the User by whom this passenger ticket was booked

    public PassengerTicket(String name, String gender, int age, String address
            , String passportNum, Flight flight, User bookedBy,String bookStatus)
    {
        passengerCount++;//incrementing passenger count
        this.ID = passengerCount;//unique ID for every passenger

        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.passportNum = passportNum;
        this.bookStatus = bookStatus;
        this.flight = flight;
        this.bookedBy = bookedBy;

        System.out.println("Ticket Reserved for " + this.name);

    }

    public String getFileFormatPassengerTicketDetails()
    {
        return(Integer.toString(this.ID)+","+this.name+","+this.gender+","+this.age+","+this.address
                +","+this.passportNum+","+this.flight.getFlightNumber()+","+this.bookedBy.getUserID()+","+this.bookStatus);
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}

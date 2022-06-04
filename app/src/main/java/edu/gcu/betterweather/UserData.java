package edu.gcu.betterweather;


public class UserData {

    private int ID;
    private String email;
    private String fName;
    private String currentLocation;
    private String altCity1;
    private String altCity2;

    public UserData(){
        // Default
    }


    public UserData(int id, String email, String fName, String currentLocation, String altCity1,
                    String altCity2) {
        ID = id;
        this.email = email;
        this.fName = fName;
        this.currentLocation = currentLocation;
        this.altCity1 = altCity1;
        this.altCity2 = altCity2;
    }

    // constructor for initial setup
    public UserData(int id, String email, String fName, String currentLocation) {
        ID = id;
        this.email = email;
        this.fName = fName;
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", fName='" + fName + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", altCity1='" + altCity1 + '\'' +
                ", altCity2='" + altCity2 + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getAltCity1() {
        return altCity1;
    }

    public void setAltCity1(String altCity1) {
        this.altCity1 = altCity1;
    }

    public String getAltCity2() {
        return altCity2;
    }

    public void setAltCity2(String altCity2) {
        this.altCity2 = altCity2;
    }
}

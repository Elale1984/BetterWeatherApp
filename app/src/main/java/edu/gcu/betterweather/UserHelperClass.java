package edu.gcu.betterweather;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserHelperClass {

     String name;
     String email;
     String currentCity;
     String secondCity;
     String thirdCity;

    public String getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(String secondCity) {
        this.secondCity = secondCity;
    }

    public String getThirdCity() {
        return thirdCity;
    }

    public void setThirdCity(String thirdCity) {
        this.thirdCity = thirdCity;
    }

    public UserHelperClass(String name, String email, String currentCity, String secondCity, String thirdCity) {
        this.name = name;
        this.email = email;
        this.currentCity = currentCity;
        this.secondCity = secondCity;
        this.thirdCity = thirdCity;
    }

    public UserHelperClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
}

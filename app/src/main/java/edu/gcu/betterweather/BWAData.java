package edu.gcu.betterweather;

public class BWAData {
    private String currDay;
    private String currTemp;
    private String currWindSpeed;
    private String currUVIndexLevel;
    private String currHumidity;
    private String highTemp;
    private String lowTemp;



    public BWAData(String currDay, String currTemp, String currWindSpeed, String currUVIndexLevel, String currHumidity, String highTemp, String lowTemp) {
        this.currDay = currDay;
        this.currTemp = currTemp;
        this.currWindSpeed = currWindSpeed;
        this.currUVIndexLevel = currUVIndexLevel;
        this.currHumidity = currHumidity;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    public String getCurrDay() {
        return currDay;
    }

    public void setCurrDay(String currDay) {
        this.currDay = currDay;
    }

    public String getCurrTemp() {
        return currTemp;
    }

    public void setCurrTemp(String currTemp) {
        this.currTemp = currTemp;
    }

    public String getCurrWindSpeed() {
        return currWindSpeed;
    }

    public void setCurrWindSpeed(String currWindSpeed) {
        this.currWindSpeed = currWindSpeed;
    }

    public String getCurrUVIndexLevel() {
        return currUVIndexLevel;
    }

    public void setCurrUVIndexLevel(String currUVIndexLevel) {
        this.currUVIndexLevel = currUVIndexLevel;
    }

    public String getCurrHumidity() { return currHumidity;}

    public void setCurrHumidity(String currHumidity) { this.currHumidity = currHumidity; }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }
}

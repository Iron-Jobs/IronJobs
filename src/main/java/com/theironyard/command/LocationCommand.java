package com.theironyard.command;

/**
 * Created by EddyJ on 8/5/16.
 */
public class LocationCommand {
    private String city;

    private String state;

    public LocationCommand() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package com.example.alphacar;

import java.util.Date;

public class danVO {
    private Date event_time;
    private double latitude;
    private double longitude;
    private String event_type;

    public danVO(Date event_time, double latitude, double longitude, String event_type) {
        this.event_time = event_time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.event_type = event_type;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }
}

package com.example.alphacar;

import java.util.Date;

public class danVO {
    private String event_time;
    private String latitude;
    private String longitude;
    private String event_type;

    public danVO(String event_time, String latitude, String longitude, String event_type) {
        this.event_time = event_time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.event_type = event_type;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }
}

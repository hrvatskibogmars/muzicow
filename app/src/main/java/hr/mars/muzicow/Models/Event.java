package hr.mars.muzicow.Models;

/**
 * Created by mars on 24/12/15.
 */
public class Event {
    String event_ID;
    String dj_ID;
    String name;
    String longitude;
    String latitude;
    String genre;
    String status;

    public String getEvent_ID() {
        return event_ID;
    }

    public void setEvent_ID(String event_ID) {
        this.event_ID = event_ID;
    }

    public String getDj_ID() {
        return dj_ID;
    }

    public void setDj_ID(String dj_ID) {
        this.dj_ID = dj_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

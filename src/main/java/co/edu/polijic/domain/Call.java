package co.edu.polijic.domain;

import java.sql.Date;

public class Call {
    private Long id;
    private Long phoneNumber;
    private Date date;
    private Double lat;
    private Double lng;

    public Call() {
    }

    public Call(Long phoneNumber, Date date) {
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public Call(Long id, Long phoneNumber, Date date) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}

package co.edu.polijic.domain;


import java.io.Serializable;
import java.sql.Date;

public class Ride implements Serializable {
    private Long id;
    private String address;
    private String responsibleName;
    private Date initDate;
    private Date endDate;
    private Long idDriver;
    private Long idCall;

    public Ride() {
    }

    public Ride(String address, String responsibleName, Date initDate, Long idDriver, Long idCall) {
        this.address = address;
        this.responsibleName = responsibleName;
        this.initDate = initDate;
        this.idDriver = idDriver;
        this.idCall = idCall;
    }

    public Ride(Long id, String address, String responsibleName, Date initDate, Date endDate, Long idDriver, Long idCall) {
        this.id = id;
        this.address = address;
        this.responsibleName = responsibleName;
        this.initDate = initDate;
        this.endDate = endDate;
        this.idDriver = idDriver;
        this.idCall = idCall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Long idDriver) {
        this.idDriver = idDriver;
    }

    public Long getIdCall() {
        return idCall;
    }

    public void setIdCall(Long idCall) {
        this.idCall = idCall;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                ", initDate=" + initDate +
                ", endDate=" + endDate +
                ", idDriver=" + idDriver +
                ", idCall=" + idCall +
                '}';
    }

}

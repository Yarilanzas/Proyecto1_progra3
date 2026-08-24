package org.domain;
import java.util.*;
import java.util.Date;

public class Reservation {
    private String id;
    private String activity;
    private Employee employee;
    private Date date;
    private String startTime;
    private String status; // estos dos no se si son strings
    private String endTime;
    private List<DetailReservation> details = new ArrayList<>();

    public Reservation(){}

    public Reservation(String id,String activity, String endTime, String status, String startTime, Date date, Employee employee) {
        this.id = id;
        this.activity = activity;
        this.endTime = endTime;
        this.status = status;
        this.startTime = startTime;
        this.date = date;
        this.employee = employee;
    }

    public List<DetailReservation> getDetails() {
        return details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDetails(List<DetailReservation> details) {
        this.details = details;
    }
}
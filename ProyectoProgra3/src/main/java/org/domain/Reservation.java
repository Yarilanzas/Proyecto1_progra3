package org.domain;
import java.util.*;
import java.util.Date;

public class Reservation {
    private String id;
    private Employee employee;
    private Date date;
    private String startTime;
    private String status; // estos dos no se si son strings
    private String endTime;
    private List<DetailReservation> details = new ArrayList<>();

    public Reservation(String id, String endTime, String status, String startTime, Date date, Employee employee) {
        this.id = id;
        this.endTime = endTime;
        this.status = status;
        this.startTime = startTime;
        this.date = date;
        this.employee = employee;
    }

    public List<DetailReservation> getDetails() {
        return details;
    }
}
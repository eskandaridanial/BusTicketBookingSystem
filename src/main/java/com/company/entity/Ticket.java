package com.company.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tbl_ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "travel_time" , nullable = false , updatable = false)
    private String time;
    @Column(name = "travel_date" , nullable = false , updatable = false)
    private Date date;
    @Column(name = "travel_number" , unique = true , updatable = false)
    private Long travelNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_passanger")
    private Passanger passanger;
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    public Ticket() {
    }

    public Ticket(String time, Date date, Route route, Long travelNumber) {
        this.time = time;
        this.date = date;
        this.route = route;
        this.travelNumber = travelNumber;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTravelNumber() {
        return travelNumber;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public void setPassanger(Passanger passanger) {
        this.passanger = passanger;
    }

    public Route getRoute() {
        return route;
    }
}

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
    private LocalTime time;
    @Column(name = "travel_date" , nullable = false , updatable = false)
    private Date date;
    @Column(name = "travel_number" , unique = true , updatable = false)
    private Long travelNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_passanger")
    private Passanger passanger;
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;
    @Column(name = "is_expired" , nullable = false , updatable = false)
    private Boolean isExpired;
    @Column(name = "is_sold" , nullable = false , updatable = false)
    private Boolean isSold;

    public Ticket() {
    }

    public Ticket(LocalTime time, Date date, Route route, Boolean isExpired , Boolean isSold) {
        this.time = time;
        this.date = date;
        this.route = route;
        this.isExpired = isExpired;
        this.isSold = isSold;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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

    public void setTravelNumber(Long travelNumber) {
        this.travelNumber = travelNumber;
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

    public void setRoute(Route route) {
        this.route = route;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }
}

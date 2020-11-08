package com.company.entity;

import com.company.authService.entity.PassangerAuthToken;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_passanger")
public class Passanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true , updatable = false)
    private String username;
    @Column(nullable = false , updatable = false)
    private String password;
    @Column(nullable = false , unique = true , updatable = false)
    private String salt;
    @Column(nullable = false , updatable = false)
    private String gender;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    @OneToOne(fetch = FetchType.LAZY)
    private PassangerAuthToken token;


    public Passanger() {
    }

    public Passanger(String username, String password, String gender , String salt) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getGender() {
        return gender;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket){
        this.tickets.remove(ticket);
    }

    public PassangerAuthToken getToken() {
        return token;
    }

    public void setToken(PassangerAuthToken token) {
        this.token = token;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

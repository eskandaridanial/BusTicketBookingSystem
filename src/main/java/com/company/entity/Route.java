package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_point" , nullable = false , updatable = false)
    private String from;
    @Column(name = "end_point" , nullable = false , updatable = false)
    private String to;

    public Route() {
    }

    public Route(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

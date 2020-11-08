package com.company.authService.entity;

import com.company.entity.Passanger;

import javax.persistence.*;

@Entity
@Table(name = "tbl_passanger_token")
public class PassangerAuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true , updatable = false)
    private String selector;
    @Column(nullable = false , unique = true , updatable = false)
    private String validator;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passanger_id")
    private Passanger passanger;

    public PassangerAuthToken() {
    }

    public PassangerAuthToken(String selector, String validator, Passanger passanger) {
        this.selector = selector;
        this.validator = validator;
        this.passanger = passanger;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public void setPassanger(Passanger passanger) {
        this.passanger = passanger;
    }
}

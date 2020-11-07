package com.company.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Passanger.class)
public abstract class Passanger_ {

	public static volatile SingularAttribute<Passanger, String> password;
	public static volatile SingularAttribute<Passanger, String> salt;
	public static volatile ListAttribute<Passanger, Ticket> tickets;
	public static volatile SingularAttribute<Passanger, String> gender;
	public static volatile SingularAttribute<Passanger, Long> id;
	public static volatile SingularAttribute<Passanger, String> username;

	public static final String PASSWORD = "password";
	public static final String SALT = "salt";
	public static final String TICKETS = "tickets";
	public static final String GENDER = "gender";
	public static final String ID = "id";
	public static final String USERNAME = "username";

}


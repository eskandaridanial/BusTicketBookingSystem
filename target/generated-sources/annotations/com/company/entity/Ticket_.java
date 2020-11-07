package com.company.entity;

import java.time.LocalTime;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

	public static volatile SingularAttribute<Ticket, Date> date;
	public static volatile SingularAttribute<Ticket, Passanger> passanger;
	public static volatile SingularAttribute<Ticket, Route> route;
	public static volatile SingularAttribute<Ticket, Boolean> isSold;
	public static volatile SingularAttribute<Ticket, Long> travelNumber;
	public static volatile SingularAttribute<Ticket, Long> id;
	public static volatile SingularAttribute<Ticket, LocalTime> time;
	public static volatile SingularAttribute<Ticket, Boolean> isExpired;

	public static final String DATE = "date";
	public static final String PASSANGER = "passanger";
	public static final String ROUTE = "route";
	public static final String IS_SOLD = "isSold";
	public static final String TRAVEL_NUMBER = "travelNumber";
	public static final String ID = "id";
	public static final String TIME = "time";
	public static final String IS_EXPIRED = "isExpired";

}

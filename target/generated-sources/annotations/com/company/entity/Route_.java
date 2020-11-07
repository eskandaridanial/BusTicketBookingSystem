package com.company.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Route.class)
public abstract class Route_ {

	public static volatile SingularAttribute<Route, String> from;
	public static volatile SingularAttribute<Route, Long> id;
	public static volatile SingularAttribute<Route, String> to;

	public static final String FROM = "from";
	public static final String ID = "id";
	public static final String TO = "to";

}


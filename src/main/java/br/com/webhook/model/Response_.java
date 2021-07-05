package br.com.webhook.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Response.class)
public class Response_ {
    public static volatile SingularAttribute<Response, Integer> statusCode;
    public static volatile SingularAttribute<Response, String> response;
    public static volatile SingularAttribute<Response, Request> request;
}

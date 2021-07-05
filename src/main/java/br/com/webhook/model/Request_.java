package br.com.webhook.model;

import br.com.webhook.dto.request.MediaTypeDto;
import org.springframework.http.HttpMethod;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class Request_ {
    public static volatile SingularAttribute<Request, String> eventUuid;
    public static volatile SingularAttribute<Request, String> url;
    public static volatile SingularAttribute<Request, String> headers;
    public static volatile SingularAttribute<Request, String> query;
    public static volatile SingularAttribute<Request, HttpMethod> method;
    public static volatile SingularAttribute<Request, MediaTypeDto> contentType;
    public static volatile SingularAttribute<Request, String> body;
    public static volatile SetAttribute<Request, TagAssociation> tagAssociations;
}

package br.com.webhook.dto.request;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;

@AllArgsConstructor
public enum MediaTypeDto {
    APPLICATION_JSON("application/json"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded");

    private final String value;

    public MediaType getMediaType() {
        return MediaType.parseMediaType(this.value);
    }
}

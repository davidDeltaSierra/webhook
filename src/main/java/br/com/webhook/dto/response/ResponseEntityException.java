package br.com.webhook.dto.response;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

@With
@Value
@Builder
@Jacksonized
public class ResponseEntityException {
    Long timestamp;
    String message;
    HttpStatus status;
}

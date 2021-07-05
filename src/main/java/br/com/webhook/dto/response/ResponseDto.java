package br.com.webhook.dto.response;

import br.com.webhook.dto.request.RequestDto;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@With
@Value
@Builder
@Jacksonized
public class ResponseDto {
    int statusCode;
    String response;
    RequestDto request;
}

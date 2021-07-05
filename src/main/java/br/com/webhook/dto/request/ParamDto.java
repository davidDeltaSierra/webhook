package br.com.webhook.dto.request;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@With
@Value
@Builder
@Jacksonized
public class ParamDto {
    @NotEmpty
    String key;
    @NotEmpty
    String value;
}

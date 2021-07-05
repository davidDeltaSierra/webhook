package br.com.webhook.dto.response;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@With
@Value
@Builder
@Jacksonized
public class EventDto {
    String uuid;
    LocalDateTime date;
}

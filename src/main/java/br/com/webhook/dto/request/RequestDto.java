package br.com.webhook.dto.request;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@With
@Value
@Builder
@Jacksonized
public class RequestDto {
    String eventUuid;
    @NotEmpty
    String url;
    @Valid
    List<ParamDto> headers;
    @Valid
    List<ParamDto> query;
    @NotNull
    HttpMethod method;
    @lombok.Builder.Default
    MediaTypeDto contentType = MediaTypeDto.APPLICATION_JSON;
    Map<String, Object> body;
    List<String> tags;

    public String parseUrl() {
        if (isNull(query) || query.isEmpty()) {
            return url;
        }
        String stringQuery = query
                .stream()
                .map(it -> it.getKey() + "={" + it.getKey() + "}")
                .collect(Collectors.joining("&"));
        return url + "?" + stringQuery;
    }
}

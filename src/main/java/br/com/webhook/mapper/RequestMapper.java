package br.com.webhook.mapper;

import br.com.webhook.config.ObjectMapperConfig;
import br.com.webhook.dto.request.ParamDto;
import br.com.webhook.dto.request.RequestDto;
import br.com.webhook.model.Request;
import br.com.webhook.model.Tag;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    Request requestDtoToRequest(RequestDto requestDto);

    RequestDto requestToRequestDto(Request request);

    default String listParamDtoToString(List<ParamDto> params) {
        if (isNull(params)) {
            return null;
        }
        return params.stream()
                .map(it -> it.getKey() + "=" + it.getValue())
                .collect(Collectors.joining(";"));
    }

    default List<ParamDto> stringToListParamDto(String param) {
        if (isNull(param)) {
            return null;
        }
        return Arrays.stream(param.split(";"))
                .map(this::factoryParamDto)
                .collect(Collectors.toList());
    }

    default String mapToString(Map<String, Object> body) {
        if (isNull(body)) {
            return null;
        }
        try {
            return ObjectMapperConfig.getObjectMapperBean().writeValueAsString(body);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    default Map<String, Object> stringToMap(String body) {
        if (isNull(body)) {
            return null;
        }
        try {
            return ObjectMapperConfig.getObjectMapperBean().readValue(body, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    default Set<Tag> listStringToListTag(List<String> tags) {
        if (isNull(tags)) {
            return null;
        }
        return tags.stream()
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    default List<String> listTagToListString(Set<Tag> tags) {
        if (isNull(tags)) {
            return null;
        }
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

    private ParamDto factoryParamDto(String it) {
        String[] split = it.split("=");
        return ParamDto.builder()
                .key(split[0])
                .value(split[1])
                .build();
    }
}

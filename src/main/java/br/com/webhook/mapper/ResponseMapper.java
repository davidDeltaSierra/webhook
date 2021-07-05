package br.com.webhook.mapper;

import br.com.webhook.dto.response.ResponseDto;
import br.com.webhook.model.Response;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RequestMapper.class)
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    ResponseDto ResponseToResponseDto(Response response);
}

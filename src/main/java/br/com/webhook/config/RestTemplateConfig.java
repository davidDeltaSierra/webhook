package br.com.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final ObjectMapper objectMapper;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(getMessageConverters());
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        return List.of(
                mappingJackson2HttpMessageConverter(),
                formHttpMessageConverter(),
                stringHttpMessageConverter(),
                byteArrayHttpMessageConverter()
        );
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        return mappingJackson2HttpMessageConverter;
    }

    private FormHttpMessageConverter formHttpMessageConverter() {
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.setSupportedMediaTypes(
                List.of(
                        MediaType.MULTIPART_FORM_DATA,
                        MediaType.APPLICATION_FORM_URLENCODED
                )
        );
        return formHttpMessageConverter;
    }

    private StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        stringHttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));
        return stringHttpMessageConverter;
    }

    private ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        byteArrayHttpMessageConverter.setSupportedMediaTypes(
                List.of(
                        MediaType.IMAGE_JPEG,
                        MediaType.IMAGE_PNG,
                        MediaType.APPLICATION_PDF,
                        MediaType.APPLICATION_OCTET_STREAM
                )
        );
        return byteArrayHttpMessageConverter;
    }
}

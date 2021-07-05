package br.com.webhook.controller.v1;

import br.com.webhook.dto.request.RequestDto;
import br.com.webhook.dto.response.EventDto;
import br.com.webhook.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/request")
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<EventDto> publisherNewWebhookRequest(@RequestBody @Valid RequestDto requestDto) {
        return new ResponseEntity<>(
                requestService.publisherNewWebhookRequest(requestDto),
                HttpStatus.ACCEPTED
        );
    }
}

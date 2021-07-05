package br.com.webhook.service;

import br.com.webhook.dto.request.RequestDto;
import br.com.webhook.dto.response.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {

    public EventDto publisherNewWebhookRequest(RequestDto requestDto) {
        log.info("Publisher new request in RabbitMQ: {}", requestDto);
        //Event<RequestDto, EventConfig> event = WebhookFactory.WEBHOOK_QUEUE.newInstance(requestDto);
        //eventPublisher.publisher(event);
        //return EventMapper.INSTANCE.eventToEventDto(event);
        return null;
    }
}

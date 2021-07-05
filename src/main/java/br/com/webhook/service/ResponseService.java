package br.com.webhook.service;

import br.com.webhook.dto.response.EventDto;
import br.com.webhook.mapper.RequestMapper;
import br.com.webhook.model.Response;
import br.com.webhook.repository.ResponseRepository;
import br.com.webhook.repository.specification.ResponseSpecification;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final TransactionTemplate transactionTemplate;
    private final ResponseRepository responseRepository;
    private final RequestService requestService;
    private final TagService tagService;

    public Response saveWithTags(final Response response, final List<String> tags) {
        return transactionTemplate.execute((status) -> {
            Response resp = save(response);
            if (nonNull(tags) && !tags.isEmpty()) {
                tagService.associationWithTag(tags, resp.getRequest());
            }
            return resp;
        });
    }

    public Response save(Response response) {
        return responseRepository.save(response);
    }

    public List<Response> findAllByStatusCodeIn(List<Integer> code, List<String> tags) {
        return responseRepository.findAll(
                Specification
                        .where(ResponseSpecification.findAllByStatusCodeIn(code))
                        .and(ResponseSpecification.findAllByTagsIn(tags)),
                EntityGraphs.named("Response.full")
        );
    }

    public Response findByRequestEventUuid(String uuid) {
        return responseRepository.findByRequest_EventUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Register not found"));
    }

    public EventDto reprocess(String uuid) {
        Response response = findByRequestEventUuid(uuid);
        return requestService.publisherNewWebhookRequest(
                RequestMapper.INSTANCE.requestToRequestDto(response.getRequest())
        );
    }

    public void deleteByRequestEventUuid(String uuid) {
        Response response = findByRequestEventUuid(uuid);
        transactionTemplate.execute((status) -> {
            responseRepository.delete(response);
            tagService.deleteByIdentifier(response.getRequest().getId().toString());
            return true;
        });
    }
}

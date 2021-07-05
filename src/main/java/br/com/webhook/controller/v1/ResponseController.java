package br.com.webhook.controller.v1;

import br.com.webhook.dto.response.EventDto;
import br.com.webhook.dto.response.ResponseDto;
import br.com.webhook.mapper.ResponseMapper;
import br.com.webhook.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/response")
public class ResponseController {
    private final ResponseService responseService;

    @GetMapping
    public ResponseEntity<?> findAllByStatusCodeIn(@RequestParam List<Integer> status,
                                                   @RequestParam(required = false) List<String> tags) {
        return new ResponseEntity<>(
                responseService.findAllByStatusCodeIn(status, tags),
                HttpStatus.OK
        );
    }

    @GetMapping("uuid/{uuid}")
    public ResponseEntity<ResponseDto> findByRequestEventUuid(@PathVariable String uuid) {
        return new ResponseEntity<>(
                ResponseMapper.INSTANCE.ResponseToResponseDto(
                        responseService.findByRequestEventUuid(uuid)
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("uuid/{uuid}")
    public ResponseEntity<ResponseDto> deleteByRequestEventUuid(@PathVariable String uuid) {
        responseService.deleteByRequestEventUuid(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("uuid/{uuid}/reprocess")
    public ResponseEntity<EventDto> reprocess(@PathVariable String uuid) {
        return new ResponseEntity<>(responseService.reprocess(uuid), HttpStatus.ACCEPTED);
    }
}

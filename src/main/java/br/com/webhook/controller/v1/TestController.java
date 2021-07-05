package br.com.webhook.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> testGet(@RequestHeader Map<String, String> headers,
                                     @RequestParam Map<String, String> query) {
        log.info("Headers: {}", headers);
        log.info("Query: {}", query);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> testPost(@RequestHeader Map<String, String> headers,
                                      @RequestParam Map<String, String> query,
                                      @RequestBody Object body) {
        log.info("Headers: {}", headers);
        log.info("Query: {}", query);
        log.info("Body: {}", body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> testPut(@RequestHeader Map<String, String> headers,
                                     @RequestParam Map<String, String> query,
                                     @RequestBody Object body) {
        log.info("Headers: {}", headers);
        log.info("Query: {}", query);
        log.info("Body: {}", body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

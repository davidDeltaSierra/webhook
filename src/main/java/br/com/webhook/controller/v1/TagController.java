package br.com.webhook.controller.v1;

import br.com.webhook.model.Tag;
import br.com.webhook.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<Iterable<Tag>> findAll() {
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }
}

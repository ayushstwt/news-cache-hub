package com.ayshriv.user_api.controller;

import com.ayshriv.user_api.dto.MessageResponse;
import com.ayshriv.user_api.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService service;

    public MessageController(
            MessageService service
    ) {
        this.service = service;
    }

    @GetMapping("")
    public Mono<ResponseEntity<MessageResponse<Object>>> getNews(@RequestParam(name = "date") String date) {
        return service.getNews(date)
                .flatMap(data -> Mono.just(
                        ResponseEntity.status(HttpStatus.OK)
                                .body(new MessageResponse<>
                                        ("data found", true, data))))
                .switchIfEmpty(Mono.defer(() -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).
                                body(new MessageResponse<>
                                        ("data not found, sending request to broker", false, null)))));
    }
}

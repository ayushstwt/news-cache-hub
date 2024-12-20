package com.ayshriv.user_api.service;

import reactor.core.publisher.Mono;


public interface MessageService {
    Mono<Void> publishToMessageBroker(String date);
    Mono<Object> getNews(String date);
}

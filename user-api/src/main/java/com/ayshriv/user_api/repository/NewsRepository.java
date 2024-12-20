package com.ayshriv.user_api.repository;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import reactor.core.publisher.Mono;

public interface NewsRepository{
    Mono<Object> getNews(String date);
}

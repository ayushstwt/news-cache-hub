package com.ayshriv.user_api.repository;


import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class NewsRepositoryImpl implements NewsRepository{

    private final ReactiveRedisOperations<String, Object> redisOperations;

    public NewsRepositoryImpl(
            ReactiveRedisOperations<String, Object> redisOperations
    ) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Mono<Object> getNews(String date) {
        return redisOperations.opsForValue().get(date);
    }
}

package com.ayshriv.user_api.service;

import com.ayshriv.user_api.repository.NewsRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewsRepository newsRepository;

    public MessageServiceImpl(
            KafkaTemplate<String, String> kafkaTemplate,
            NewsRepository newsRepository
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.newsRepository = newsRepository;
    }

    @Override
    public Mono<Void> publishToMessageBroker(String date) {
        ProducerRecord<String, String> record = new ProducerRecord<>("news", null, date);
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        return Mono.fromFuture(future)
                .then();
    }

    @Override
    public Mono<Object> getNews(String date) {
        return newsRepository.getNews(date)
                .flatMap(Mono::just)
                .switchIfEmpty(Mono.defer(() -> publishToMessageBroker(date)));
    }
}

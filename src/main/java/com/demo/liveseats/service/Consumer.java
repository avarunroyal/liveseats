package com.demo.liveseats.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.liveseats.model.LiveSeats;
import com.demo.liveseats.repository.LiveSeatsRepository;
import com.google.gson.Gson;

@Service
@ConditionalOnProperty(value = "liveseats.kafka.consumer-enabled", havingValue = "true")
@Component
public class Consumer {

    private final Logger logger =  LoggerFactory.getLogger(Consumer.class);
    
	@Autowired
	public LiveSeatsRepository liveSeatsRepository;

    @KafkaListener(topics = {"LIVE_SEAT_DATA"})
    public void consume(final @Payload String message,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                        final Acknowledgment acknowledgment
    ) {
        logger.info(String.format("#### -> Consumed message -> TIMESTAMP: %d\n%s\noffset: %d\nkey: %s\npartition: %d\ntopic: %s", ts, message, offset, key, partition, topic));
        acknowledgment.acknowledge();
        liveSeatsRepository.save(new Gson().fromJson(message, LiveSeats.class));
    }
}
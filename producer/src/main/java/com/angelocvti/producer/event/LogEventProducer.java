package com.angelocvti.producer.event;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class LogEventProducer {

    private static final Logger log = LoggerFactory.getLogger(LogEventProducer.class);
    private static final String TOPIC = "transaction-logs";
    private final KafkaTemplate<String, String> kafkaTemplate;
    // A simple in-memory buffer used as fallback demonstration
    private final ConcurrentLinkedQueue<String> fallbackBuffer = new ConcurrentLinkedQueue<>();

    public LogEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retry(name = "kafka-producer", fallbackMethod = "sendFallback")
    public void sendTransactionLog(String message) {
        log.info("Publishing log event to Kafka: {}", message);
        kafkaTemplate.send(TOPIC, message).whenComplete((r, ex) -> {
            if (ex != null) {
                log.error("Kafka send failed (async): {}", ex.getMessage());
                throw new RuntimeException(ex);
            } else {
                log.debug("Kafka send success");
            }
        });
    }

    public void sendFallback(String message, Throwable t) {
        log.warn("Writing to fallback buffer because Kafka is unavailable. Message: {}", message);
        fallbackBuffer.add(message);
    }
}

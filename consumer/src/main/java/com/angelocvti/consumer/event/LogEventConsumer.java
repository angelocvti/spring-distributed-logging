package com.angelocvti.consumer.event;

import com.angelocvti.consumer.model.LogEvent;
import com.angelocvti.consumer.repository.LogEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class LogEventConsumer {
  private static final Logger log = LoggerFactory.getLogger(LogEventConsumer.class);
  private final LogEventRepository repository;

  public LogEventConsumer(LogEventRepository repository) {
    this.repository = repository;
  }

  @KafkaListener(topics = "transaction-logs", groupId = "log-consumer-group")
  public void consume(String message) {
    log.info("Consumed log message: {}", message);

    LogEvent e = new LogEvent(UUID.randomUUID().toString(), message, Instant.now());

    repository.save(e);

    log.debug("Indexed log event to Elasticsearch id={}", e.getId());
  }
}

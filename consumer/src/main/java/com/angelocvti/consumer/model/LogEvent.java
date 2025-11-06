package com.angelocvti.consumer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;

@Document(indexName = "transaction-logs")
public class LogEvent {
  @Id private String id;
  private String message;
  private Instant timestamp;

  public LogEvent() {}

  public LogEvent(String id, String message, Instant timestamp) {
    this.id = id;
    this.message = message;
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
}

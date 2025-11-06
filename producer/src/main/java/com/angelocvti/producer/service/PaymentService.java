package com.angelocvti.producer.service;

import com.angelocvti.producer.event.LogEventProducer;
import com.angelocvti.producer.model.PaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
  private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
  private final LogEventProducer producer;

  public PaymentService(LogEventProducer producer) {
    this.producer = producer;
  }

  public void processPayment(PaymentRequest req) {
    log.info("Processing payment for {} amount={}", req.userId(), req.amount());

    String message = String.format("payment|user=%s|amount=%s", req.userId(), req.amount());

    producer.sendTransactionLog(message);
  }
}

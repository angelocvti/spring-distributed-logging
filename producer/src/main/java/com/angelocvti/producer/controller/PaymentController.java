package com.angelocvti.producer.controller;

import com.angelocvti.producer.model.PaymentRequest;
import com.angelocvti.producer.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public String process(@RequestBody PaymentRequest req) {
    paymentService.processPayment(req);
    return "OK";
  }
}

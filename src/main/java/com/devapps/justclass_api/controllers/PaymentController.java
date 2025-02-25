package com.devapps.justclass_api.controllers;

import com.devapps.justclass_api.models.payment.PaymentRequest;
import com.devapps.justclass_api.models.payment.PaymentResponse;
import com.devapps.justclass_api.services.PaymentService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
        } catch (ResponseStatusException re) {
            return ResponseEntity.status(re.getStatusCode())
                    .body(
                            PaymentResponse.builder()
                                    .message(re.getMessage())
                                    .build()
                    );
        }
    }

    @GetMapping("/by-teacher/{teacher}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByTeacher(@PathVariable String teacher) {
        try {
            List<PaymentResponse> PaymentResponses = paymentService.getPaymentsByTeacher(teacher);
            return ResponseEntity.ok(PaymentResponses);
        } catch (ResponseStatusException rx) {
            return ResponseEntity.status(rx.getStatusCode())
                    .body(List.of( PaymentResponse.builder()
                            .message(rx.getMessage())
                            .build())
                    );
        }
    }
}

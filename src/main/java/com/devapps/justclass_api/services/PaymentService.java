package com.devapps.justclass_api.services;

import com.devapps.justclass_api.models.payment.Payment;
import com.devapps.justclass_api.models.payment.PaymentRequest;
import com.devapps.justclass_api.models.payment.PaymentResponse;
import com.devapps.justclass_api.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public final PaymentRepository paymentRepository;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        try {
            var payment = Payment.builder()
                    .classroom(paymentRequest.getClassroom())
                    .level(paymentRequest.getLevel())
                    .student(paymentRequest.getStudent())
                    .date(paymentRequest.getDate())
                    .amount(paymentRequest.getAmount())
                    .teacher(paymentRequest.getTeacher())
                    .build();
            paymentRepository.save(payment);
            return PaymentResponse.builder()
                    .message(payment.getStudent() + "'s payment has been made")
                    .build();
        } catch (Exception e) {
            return PaymentResponse.builder()
                    .message("Error occurred: " + e.getLocalizedMessage())
                    .build();
        }
    }

    public List<PaymentResponse> getPaymentsByTeacher(String teacher) {

        try {
            List<Payment> payments = paymentRepository.getPaymentsByTeacher(teacher);

            return payments.stream()
                    .map(pay -> PaymentResponse.builder()
                            .paymentid(pay.getPaymentid())
                            .student(pay.getStudent())
                            .level(pay.getLevel())
                            .amount(pay.getAmount())
                            .date(pay.getDate())
                            .teacher(pay.getTeacher())
                            .classroom(pay.getClassroom())
                            .build())
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A problem occurred: " + e.getLocalizedMessage());
        }
    }
}

package com.devapps.justclass_api.models.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private UUID paymentid;
    private String student;
    private String classroom;
    private String level;
    private String amount;
    private String date;
    private String teacher;
    private String message;
}

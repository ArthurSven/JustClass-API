package com.devapps.justclass_api.models.student;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private UUID studentid;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneno;
    private String datejoined;
    private String teacher;
    private String message;
}

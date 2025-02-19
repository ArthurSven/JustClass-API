package com.devapps.justclass_api.models.student;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String studentid;
    private String firstname;
    private String lastname;
    private String datejoined;
    private String teacher;
    private String message;
}

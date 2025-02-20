package com.devapps.justclass_api.controllers;

import com.devapps.justclass_api.models.student.StudentRequest;
import com.devapps.justclass_api.models.student.StudentResponse;
import com.devapps.justclass_api.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        try {
            return ResponseEntity.ok(studentService.createStudent(request));
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(StudentResponse.builder()
                            .message(ex.getReason())
                            .build()
                    );
        }
    }

    @GetMapping("/by-teacher/{teacher}")
    public ResponseEntity<List< StudentResponse>> getStudentsByTeacher(@PathVariable String teacher) {
        try {
            List< StudentResponse>  StudentResponses = studentService.getStudentsByTeacher(teacher);
            return ResponseEntity.ok( StudentResponses);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(List.of( StudentResponse.builder()
                            .message(ex.getReason())
                            .build()));
        }
    }

    @GetMapping("by-id/{studentid}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable UUID studentid) {
        try {
            StudentResponse studentResponse = studentService.getStudentById(studentid);
            return ResponseEntity.ok(studentResponse);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(StudentResponse.builder()
                            .message(ex.getLocalizedMessage())
                            .build());
        }
    }

}

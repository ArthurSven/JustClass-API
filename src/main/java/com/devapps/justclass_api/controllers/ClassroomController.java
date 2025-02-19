package com.devapps.justclass_api.controllers;

import com.devapps.justclass_api.models.classroom.ClassroomRequest;
import com.devapps.justclass_api.models.classroom.ClassroomResponse;
import com.devapps.justclass_api.services.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<ClassroomResponse> createClassroom(@RequestBody ClassroomRequest request) {
        try {
            return ResponseEntity.ok(classroomService.createClassroom(request));
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(ClassroomResponse.builder()
                            .responseMessage(ex.getReason())
                            .build()
                    );
        }
    }

    @GetMapping("/{createdby}")
    public ResponseEntity<List<ClassroomResponse>> getClassroomByTeacher(@PathVariable String createdby) {
        try {
            List<ClassroomResponse> classroomResponses = classroomService.getClassroomByTeacher(createdby);
            return ResponseEntity.ok(classroomResponses);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(List.of(ClassroomResponse.builder()
                            .responseMessage(ex.getReason())
                            .build()));
        }
    }
}

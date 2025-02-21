package com.devapps.justclass_api.controllers;

import com.devapps.justclass_api.models.homework.HomeworkRequest;
import com.devapps.justclass_api.models.homework.HomeworkResponse;
import com.devapps.justclass_api.services.HomeworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;


    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @PostMapping
    ResponseEntity<HomeworkResponse> createHomework(@RequestBody HomeworkRequest homeworkRequest) {
        try {
           return ResponseEntity.ok(homeworkService.createHomework(homeworkRequest));
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(HomeworkResponse.builder()
                            .page(0)
                            .message("Error: " + ex.getReason())
                            .build());
        }
    }

    @GetMapping("/by-teacher/{teacher}")
    ResponseEntity<List<HomeworkResponse>> getHomeworkByTeacher(@PathVariable String teacher) {
        try {
            List<HomeworkResponse> homeworkResponse = homeworkService.getHomeworkByTeacher(teacher);
            return ResponseEntity.ok(homeworkResponse);
        } catch (ResponseStatusException rx) {
            return ResponseEntity.status(rx.getStatusCode())
                    .body(List.of(HomeworkResponse.builder()
                                    .page(0)
                                    .message("Error: " + rx.getReason())
                            .build()));
        }
    }
}

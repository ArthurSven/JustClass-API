package com.devapps.justclass_api.services;

import com.devapps.justclass_api.models.student.Student;
import com.devapps.justclass_api.models.student.StudentRequest;
import com.devapps.justclass_api.models.student.StudentResponse;
import com.devapps.justclass_api.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {

        try {
            var student = Student.builder()
                    .firstname(studentRequest.getFirstname())
                    .lastname(studentRequest.getLastname())
                    .datejoined(studentRequest.getDatejoined())
                    .teacher(studentRequest.getTeacher())
                    .build();
            studentRepository.save(student);
            return StudentResponse.builder()
                    .message(studentRequest.getFirstname() + "'s profile has been created")
                    .build();
        } catch (Exception e) {
            return StudentResponse.builder()
                    .message("Error occurred: " + e.getLocalizedMessage())
                    .build();
        }
    }

    public List<StudentResponse> getStudentsByTeacher(StudentRequest studentRequest) {
        try {

        }
    }
}

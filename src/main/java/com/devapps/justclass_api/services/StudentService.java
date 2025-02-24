package com.devapps.justclass_api.services;

import com.devapps.justclass_api.models.student.Student;
import com.devapps.justclass_api.models.student.StudentRequest;
import com.devapps.justclass_api.models.student.StudentResponse;
import com.devapps.justclass_api.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

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
                    .email(studentRequest.getEmail())
                    .phoneno(studentRequest.getPhoneno())
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

    public List<StudentResponse> getStudentsByTeacher(String teacher) {
        try {
            List<Student> students = studentRepository.getStudentsByTeacher(teacher);
            return students.stream()
                    .map(student -> StudentResponse.builder()
                            .studentid(student.getStudentid())
                            .firstname(student.getFirstname())
                            .lastname(student.getLastname())
                            .datejoined(student.getDatejoined())
                            .email(student.getEmail())
                            .phoneno(student.getPhoneno())
                            .teacher(student.getTeacher())
                            .build())
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A problem occurred: " + e.getLocalizedMessage());
        }
    }

    public StudentResponse getStudentById(UUID studentID) {

        try {
            Student student = studentRepository.getStudentById(studentID)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

            return StudentResponse.builder()
                    .studentid(student.getStudentid())
                    .firstname(student.getFirstname())
                    .lastname(student.getLastname())
                    .email(student.getEmail())
                    .phoneno(student.getPhoneno())
                    .datejoined(student.getDatejoined())
                    .teacher(student.getTeacher())
                    .build();
        } catch (ResponseStatusException re) {
            throw new RuntimeException("A problem occured: " + re.getLocalizedMessage());
        }
    }
}

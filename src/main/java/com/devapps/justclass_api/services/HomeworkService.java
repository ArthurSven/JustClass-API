package com.devapps.justclass_api.services;

import com.devapps.justclass_api.models.classroom.Classroom;
import com.devapps.justclass_api.models.classroom.ClassroomResponse;
import com.devapps.justclass_api.models.homework.Homework;
import com.devapps.justclass_api.models.homework.HomeworkRequest;
import com.devapps.justclass_api.models.homework.HomeworkResponse;
import com.devapps.justclass_api.repositories.HomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public HomeworkResponse createHomework(HomeworkRequest homeworkRequest) {
        try {
            var homework = Homework.builder()
                    .homework(homeworkRequest.getHomework())
                    .book(homeworkRequest.getBook())
                    .page(homeworkRequest.getPage())
                    .createdate(homeworkRequest.getCreatedate())
                    .duedate(homeworkRequest.getDuedate())
                    .classroom(homeworkRequest.getClassroom())
                    .teacher(homeworkRequest.getTeacher())
                    .build();
            homeworkRepository.save(homework);
            return HomeworkResponse.builder()
                    .message(homework.getHomework() + " has been created")
                    .page(homeworkRequest.getPage())
                    .build();
        } catch (Exception e) {
            return HomeworkResponse.builder()
                    .page(0)
                    .message("Error: " + e.getLocalizedMessage())
                    .build();
        }
    }

    public List<HomeworkResponse> getHomeworkByTeacher(String teacher) {
        try {
            List<Homework> homework = homeworkRepository.getHomeworksByTeacher(teacher);

            return homework.stream()
                    .map(homeworks -> HomeworkResponse.builder()
                            .homeworkid(homeworks.getHomeworkid())
                            .page(homeworks.getPage())
                            .book(homeworks.getBook())
                            .homework(homeworks.getHomework())
                            .classroom(homeworks.getClassroom())
                            .createdate(homeworks.getCreatedate())
                            .duedate(homeworks.getDuedate())
                            .teacher(homeworks.getTeacher())
                            .build())
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A problem occurred: " + e.getLocalizedMessage());
        }
    }
}

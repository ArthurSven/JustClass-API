package com.devapps.justclass_api.services;

import com.devapps.justclass_api.models.classroom.Classroom;
import com.devapps.justclass_api.models.classroom.ClassroomRequest;
import com.devapps.justclass_api.models.classroom.ClassroomResponse;
import com.devapps.justclass_api.repositories.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomResponse createClassroom(ClassroomRequest classroomRequest) {
        String classname = classroomRequest.getClassname();
        String level = classroomRequest.getLevel();
        Date startdate = classroomRequest.getStartdate();
        Date enddate = classroomRequest.getEnddate();
        Double price = classroomRequest.getPrice();
        Date createdate = classroomRequest.getCreatedate();
        String createdby = classroomRequest.getCreatedby();

        if(classroomRepository.findClassroomByName(classname).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class with this name already exists");
        }

        try {
            var classroom = Classroom.builder()
                    .classname(classroomRequest.getClassname())
                    .level(classroomRequest.getLevel())
                    .startdate(classroomRequest.getStartdate())
                    .enddate(enddate)
                    .price(price)
                    .createdby(createdby)
                    .createdate(createdate)
                    .build();

            classroomRepository.save(classroom);
            return ClassroomResponse.builder()
                    .responseMessage(classname + " has been created")
                    .build();

        } catch (Exception e) {
            return ClassroomResponse.builder()
                    .responseMessage("Error: " + e.getLocalizedMessage())
                    .build();
        }
    }

    public ClassroomResponse getClassroomByName(ClassroomResponse classroomResponse) {
        try {
            var classroom = (Classroom) classroomRepository.findClassroomByName(classroomResponse.getClassname()).orElseThrow();
            return ClassroomResponse.builder()
                    .classid(classroomResponse.getClassid())
                    .classname(classroom.getClassname())
                    .level(classroomResponse.getLevel())
                    .startdate(classroomResponse.getStartdate())
                    .enddate(classroomResponse.getEnddate())
                    .price(classroomResponse.getPrice())
                    .createdate(classroomResponse.getCreatedate())
                    .createdby(classroomResponse.getCreatedby())
                    .build();
        } catch (ResponseStatusException re) {
            throw new RuntimeException("A problem occured: " + re.getLocalizedMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("A problem occured: " + e.getLocalizedMessage());
        }
    }

    public List<ClassroomResponse> getClassroomByTeacher(String createdby) {
        try {
            List<Classroom> classrooms = classroomRepository.findClassroomByTeacher(createdby);

            return classrooms.stream()
                    .map(classroom -> ClassroomResponse.builder()
                            .classid(classroom.getClassid())
                            .classname(classroom.getClassname())
                            .level(classroom.getLevel())
                            .startdate(classroom.getStartdate())  // Convert date to string
                            .enddate(classroom.getEnddate())      // Convert date to string
                            .price(classroom.getPrice())
                            .createdate(classroom.getCreatedate())  // Convert date to string
                            .createdby(classroom.getCreatedby())  // Ensure correct reference to teacher
                            .build())
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A problem occurred: " + e.getLocalizedMessage());
        }
    }

}

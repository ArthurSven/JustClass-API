package com.devapps.justclass_api.repositories;

import com.devapps.justclass_api.models.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM Student s WHERE s.studentid = :studentid")
    Optional<Student> getStudentById(@Param("studentid") UUID studentid);

    @Query("SELECT s FROM Student s WHERE s.teacher = :teacher")
    List <Student> getStudentsByTeacher(@Param("teacher") String teacher);

}

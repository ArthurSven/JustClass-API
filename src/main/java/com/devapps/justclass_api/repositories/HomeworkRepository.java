package com.devapps.justclass_api.repositories;

import com.devapps.justclass_api.models.homework.Homework;
import com.devapps.justclass_api.models.homework.HomeworkResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HomeworkRepository extends JpaRepository<Homework, UUID> {

    @Query("SELECT h FROM Homework h WHERE h.homeworkid = :homeworkid")
    Optional<Homework> getHomeworkById(@Param("homeworkid") UUID homeworkid);

    @Query("SELECT h FROM Homework h WHERE h.teacher = :teacher")
    List<Homework> getHomeworksByTeacher(@Param("teacher") String teacher);
}
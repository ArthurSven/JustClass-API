package com.devapps.justclass_api.repositories;

import com.devapps.justclass_api.models.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    @Query("SELECT c FROM Classroom c WHERE c.level = :level")
    Optional<List<Classroom>> findClassroomByLevel(@Param("level") String level);

    @Query("SELECT c FROM Classroom c WHERE c.createdby = :createdby")
    List<Classroom> findClassroomByTeacher(@Param("createdby") String createdby);

    @Query("SELECT c FROM Classroom c WHERE c.classid = :classid")
    Optional<Classroom> findClassroomById(@Param("classid") UUID classid);

    @Query("SELECT c FROM Classroom c WHERE c.classname = :classname")
    Optional<Classroom> findClassroomByName(@Param("classname") String classname);
}

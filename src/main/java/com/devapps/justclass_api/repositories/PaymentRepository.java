package com.devapps.justclass_api.repositories;

import com.devapps.justclass_api.models.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p WHERE p.teacher = :teacher")
    List<Payment> getPaymentsByTeacher(@Param("teacher") String teacher);

    @Query("SELECT p FROM Payment p WHERE p.level = :level")
    List<Payment> getPaymentsByLevel(@Param("level") String level);

    @Query("SELECT p FROM Payment p WHERE p.classroom = :classroom")
    Optional<Payment> getPaymentByClassroom(@Param("classroom") String classroom);

}

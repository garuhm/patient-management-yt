package com.patientmng.patientservice.repository;

import com.patientmng.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
//    ignores id passed in during search
    boolean existsByEmailAndIdNot(String email, UUID id);
}

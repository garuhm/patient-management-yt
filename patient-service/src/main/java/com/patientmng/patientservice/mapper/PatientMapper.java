package com.patientmng.patientservice.mapper;

import com.patientmng.patientservice.dto.PatientRequestDTO;
import com.patientmng.patientservice.dto.PatientResponseDTO;
import com.patientmng.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getDateOfBirth().toString()
        );
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        return Patient.builder()
                .id(null)
                .name(patientRequestDTO.name())
                .email(patientRequestDTO.email())
                .address(patientRequestDTO.address())
                .dateOfBirth(LocalDate.parse(patientRequestDTO.dateOfBirth()))
                .registeredDate(LocalDate.parse(patientRequestDTO.registeredDate()))
                .build();
    }

    public static void update(Patient patient, PatientRequestDTO patientRequestDTO) {
        patient.setName(patientRequestDTO.name() != null ? patientRequestDTO.name() : patient.getName());
        patient.setEmail(patientRequestDTO.email() != null ? patientRequestDTO.email() : patient.getEmail());
        patient.setAddress(patientRequestDTO.address() != null ? patientRequestDTO.address() : patient.getAddress());
        patient.setDateOfBirth(patientRequestDTO.dateOfBirth() != null ? LocalDate.parse(patientRequestDTO.dateOfBirth()) : patient.getDateOfBirth());
        patient.setRegisteredDate(patientRequestDTO.registeredDate() != null ? LocalDate.parse(patientRequestDTO.registeredDate()) : patient.getRegisteredDate());
    }
}

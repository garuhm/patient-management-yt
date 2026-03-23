package com.patientmng.patientservice.dto;

import java.util.UUID;

public record PatientResponseDTO(
        UUID id,
        String name,
        String email,
        String address,
        String dateOfBirth
) {
}

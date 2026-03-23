package com.patientmng.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientRequestDTO(
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        @NotBlank(message = "Name cannot be empty")
        String name,

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be empty")
        String email,

        @NotBlank(message = "Address cannot be empty")
        String address,

        @NotBlank(message = "Date of birth cannot be empty")
        String dateOfBirth,

        @NotBlank(message = "Registered date cannot be empty")
        String registeredDate
) {
}

package com.patientmng.patientservice.dto;

import com.patientmng.patientservice.dto.validators.CreatePatientValdiationGroup;
import com.patientmng.patientservice.dto.validators.UpdatePutPatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientRequestDTO(
        @Size(max = 100, message = "Name cannot exceed 100 characters")
//        only active when the group is specified in the controller
        @NotBlank(
                groups = {CreatePatientValdiationGroup.class, UpdatePutPatientValidationGroup.class},
                message = "Name cannot be empty"
        )
        String name,

        @Email(message = "Invalid email format")
        @NotBlank(
                groups = {CreatePatientValdiationGroup.class, UpdatePutPatientValidationGroup.class},
                message = "Email cannot be empty"
        )
        String email,

        @NotBlank(
                groups = {CreatePatientValdiationGroup.class, UpdatePutPatientValidationGroup.class},
                message = "Address cannot be empty"
        )
        String address,

        @NotBlank(
                groups = {CreatePatientValdiationGroup.class, UpdatePutPatientValidationGroup.class},
                message = "Date of birth cannot be empty"
        )
        String dateOfBirth,

        @NotBlank(groups = CreatePatientValdiationGroup.class, message = "Registered date cannot be empty")
        String registeredDate
) {
}

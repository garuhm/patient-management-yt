package com.patientmng.patientservice.controller;

import com.patientmng.patientservice.dto.PatientRequestDTO;
import com.patientmng.patientservice.dto.PatientResponseDTO;
import com.patientmng.patientservice.dto.validators.CreatePatientValdiationGroup;
import com.patientmng.patientservice.dto.validators.UpdatePutPatientValidationGroup;
import com.patientmng.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patient", description = "API for managing patient records")

@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @Operation(summary = "Get all patients")
    @GetMapping()
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @Operation(summary = "Create a new patient")
    @PostMapping()
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValdiationGroup.class})
                                                                @RequestBody PatientRequestDTO requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientService.createPatient(requestDto));
    }

    @Operation(summary = "Update an existing patient by id")
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
            @Validated({Default.class, UpdatePutPatientValidationGroup.class}) @RequestBody PatientRequestDTO requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientService.updatePatient(id, requestDto));
    }

    @Operation(summary = "Partially update an existing patient by id")
    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> partiallyUpdatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientService.updatePatient(id, requestDto));
    }

    @Operation(summary = "Delete a patient by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

package com.patientmng.patientservice.controller;

import com.patientmng.patientservice.dto.PatientRequestDTO;
import com.patientmng.patientservice.dto.PatientResponseDTO;
import com.patientmng.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")

@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping()
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientService.createPatient(requestDto));
    }
}


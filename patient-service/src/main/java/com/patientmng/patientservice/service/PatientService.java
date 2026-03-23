package com.patientmng.patientservice.service;

import com.patientmng.patientservice.dto.PatientRequestDTO;
import com.patientmng.patientservice.dto.PatientResponseDTO;
import com.patientmng.patientservice.mapper.PatientMapper;
import com.patientmng.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients() {
        return patientRepository
                .findAll()
                .stream()
                .map(PatientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        return PatientMapper
                .toDto(patientRepository.save(
                        PatientMapper.toModel(patientRequestDTO)
                ));
    }
}

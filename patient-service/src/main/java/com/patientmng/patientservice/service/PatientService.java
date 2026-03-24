package com.patientmng.patientservice.service;

import com.patientmng.patientservice.dto.PatientRequestDTO;
import com.patientmng.patientservice.dto.PatientResponseDTO;
import com.patientmng.patientservice.exception.EmailAlreadyExistsException;
import com.patientmng.patientservice.exception.PatientNotFoundException;
import com.patientmng.patientservice.mapper.PatientMapper;
import com.patientmng.patientservice.model.Patient;
import com.patientmng.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
        if(patientRepository.existsByEmail(patientRequestDTO.email())) {
            throw new EmailAlreadyExistsException("Email " + patientRequestDTO.email() + " is already associated with a patient");
        }

        return PatientMapper
                .toDto(patientRepository.save(
                        PatientMapper.toModel(patientRequestDTO)
                ));
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found"));

//        if request seeks to update email
        if(patientRequestDTO.email() != null
//        and the email isn't the same in both the dto and the entity
                && !patient.getEmail().equals(patientRequestDTO.email())
//        then it checks if another patient has that email, ignoring the entity itself
                && patientRepository.existsByEmailAndIdNot(patientRequestDTO.email(), id)) {
            throw new EmailAlreadyExistsException("Email " + patientRequestDTO.email() + " is already associated with a patient");
        }

        PatientMapper.updateModelWithDTO(patient, patientRequestDTO);
        return PatientMapper.toDto(patientRepository.save(patient));
    }

    public void deletePatient(UUID id) {
        if(!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found");
        }
        patientRepository.deleteById(id);
    }
}

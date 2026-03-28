package com.patientmng.patientservice.kafka;

import com.patientmng.patientservice.model.Patient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    //    define message types
//    send msgs with this as well
//    specifying msgs will be of key val pairs
//    key string, value byte arr
//    converts all data to byte arr with these settings
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendEvent(Patient patient) {
        PatientEvent patientEvent = PatientEvent
                .newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

//        event topic is patient
        try {
            kafkaTemplate.send("patient", patientEvent.toByteArray());
        } catch (Exception e) {
            log.error("Error sending PatientCreated event to Kafka: " + e.getMessage());
        }

    }
}

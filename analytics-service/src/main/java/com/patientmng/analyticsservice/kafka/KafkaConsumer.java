package com.patientmng.analyticsservice.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    //    group id = consumer group
    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
//            perform business logic here
            log.info("Received PatientEvent: " + patientEvent.toString());

        } catch (Exception e) {
            log.error("Error deserializing PatientEvent inside of analytics service: " + e.getMessage());
        }
    }
}

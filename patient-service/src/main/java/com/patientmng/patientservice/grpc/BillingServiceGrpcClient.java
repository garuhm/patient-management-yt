package com.patientmng.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    //    provicdes synchronous clietnt calls to grpc service (billing service)
//    will wait from response from service to continue
    private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

//    inject env vars to where bill service is
//    docker container will pass in these env vars
    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serverAddress,
                                    @Value("${billing.service.port:9001}") int serverPort) {
        log.info("connecting to billing service at: " + serverAddress + ":" + serverPort);
//        creates managed channell, assigned to blocking stub
        this.billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(
                ManagedChannelBuilder
                        .forAddress(serverAddress, serverPort)
                        .usePlaintext()
                        .build()
        );
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        log.debug("creating billing account for patient: " + patientId);

        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();
        BillingResponse response = billingServiceBlockingStub.createBillingAccount(request);

        log.info("received response from billing service: " + response.toString());
        return response;
    }
}

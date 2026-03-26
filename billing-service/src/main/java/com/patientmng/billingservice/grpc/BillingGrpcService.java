package com.patientmng.billingservice.grpc;

// billing pckg auotogenerated with mvn compile
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase{
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest request, StreamObserver<billing.BillingResponse> responseObserver) {
        log.info("create billing request received: {}", request.toString());
//        business logic goes here
        billing.BillingResponse response = billing.BillingResponse.newBuilder()
                .setAccountId(request.getPatientId())
                .setStatus("ACTIVE")
                .build();

//        sends response from service to client
        responseObserver.onNext(response);
//        response completed, end cycle
//        needed to end request, can send multiple responses
//        but need to eventually end request
        responseObserver.onCompleted();
    }
}

# Service Application Configs

Source: each service's `src/main/resources/application.yaml`

## patient-service

- `spring.application.name=patient-service`
- `spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer`
- `spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.ByteArraySerializer`
- `server.port=4000`

## billing-service

- `spring.application.name=billing-service`
- `spring.grpc.server.port=9001`
- `spring.grpc.server.reflection.enabled=true`
- `server.port=4001`

## analytics-service

- `spring.application.name=analytics-service`
- `spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer`
- `spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.ByteArrayDeserializer`
- `server.port=4003`

## api-gateway

- `spring.application.name=api-gateway`
- `spring.cloud.gateway.server.webflux.routes[0].id=patient-service-route`
- `spring.cloud.gateway.server.webflux.routes[0].uri=http://patient-service:4000`
- `spring.cloud.gateway.server.webflux.routes[0].predicates[0]=Path=/api/v1/patients/**`
- `spring.cloud.gateway.server.webflux.routes[1].id=api-docs-patient-route`
- `spring.cloud.gateway.server.webflux.routes[1].uri=http://patient-service:4000`
- `spring.cloud.gateway.server.webflux.routes[1].predicates[0]=Path=/api-docs/patients`
- `spring.cloud.gateway.server.webflux.routes[1].filters[0]=RewritePath=/api-docs/patients,/v3/api-docs`
- `server.port=4004`

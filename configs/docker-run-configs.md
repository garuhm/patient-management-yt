# Docker Run Config Backups

Source: `.idea/workspace.xml`

## billing-service

- Run config name: `billing-service`
- Server: `Docker`
- Build source: `billing-service/Dockerfile`
- Image tag: `billing-service:latest`
- Container name: `billing-service`
- Network option: `--network internal`
- Port bindings:
  - `4001:4001`
  - `9001:9001`
- Environment variables: none stored in the run config

## patient-service

- Run config name: `patient-service`
- Server: `Docker`
- Build source: `patient-service/Dockerfile`
- Image tag: `patient-service:latest`
- Container name: `patient-service`
- Network option: `--network internal`
- Port bindings: none stored in the run config
- Environment variables:
  - `SPRING_DATASOURCE_PASSWORD=password`
  - `SPRING_DATASOURCE_URL=jdbc:postgresql://patient-service-db:5432/db`
  - `SPRING_DATASOURCE_USERNAME=admin_user`
  - `SPRING_JPA_HIBERNATE_DDL_AUTO=update`
  - `SPRING_SQL_INIT_MODE=always`
  - `BILLING_SERVICE_ADDRESS=billing-service`
  - `BILLING_SERVICE_GRPC_PORT=9001`
  - `SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:29092`
- Why `kafka:29092` is used:
  - The patient-service container must use Kafka's internal Docker listener, not `localhost:9092`.
  - If the service connects to `kafka:9092`, the broker can advertise `localhost:9092` back in metadata, which makes the container try to connect to itself instead of the Kafka container.
  - `kafka:29092` keeps broker-to-client communication on the Docker network using the resolvable container hostname.

## patient-service-db

- Run config name: `patient-service-db`
- Server: `Docker`
- Build source: Docker image (not built from a Dockerfile)
- Image tag: `postgres:latest`
- Container name: `patient-service-db`
- Network option: `--network internal`
- Port bindings:
  - `5000:5432`
- Environment variables:
  - `POSTGRES_USER=admin_user`
  - `POSTGRES_PASSWORD=password`
  - `POSTGRES_DB=db`
- Volume bindings:
  - `C:\Users\garuh\Computer-Science\Coding\Backend\spring-boot-java\patient-management\db-volumes\patient-service:/var/lib/postgresql`

## analytics-service

- Run config name: `analytics-service`
- Server: `Docker`
- Build source: `analytics-service/Dockerfile`
- Image tag: `analytics-service:latest`
- Container name: `analytics-service`
- Network option: `--network internal`
- Port bindings:
  - `4003:4003`
- Environment variables:
  - `SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:29092`
- Why `kafka:29092` is used:
  - Analytics runs inside the Docker network, so it must use Kafka's internal listener.
  - This avoids the broker advertising `localhost:9092` back to the container.

## api-gateway

- Run config name: `api-gateway`
- Server: `Docker`
- Build source: `api-gateway/Dockerfile`
- Image tag: `api-gateway:latest`
- Container name: `api-gateway`
- Network option: `--network internal`
- Port bindings:
  - `4004:4004`
- Environment variables: none stored in the run config

## kafka

- Run config name: `kafka`
- Server: `Docker`
- Build source: Docker image (not built from a Dockerfile)
- Image tag: `apache/kafka:latest`
- Container name: `kafka`
- Network option: `--network internal`
- Port bindings:
  - `9092:9092`
  - `9093:9093`
- Environment variables:
  - `KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:29092,PLAINTEXT_HOST://localhost:9092`
  - `KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER`
  - `KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093`
  - `KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0`
  - `KAFKA_INTER_BROKER_LISTENER_NAME=PLAINTEXT`
  - `KAFKA_LISTENERS=PLAINTEXT://:29092,PLAINTEXT_HOST://:9092,CONTROLLER://:9093`
  - `KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT,CONTROLLER:PLAINTEXT`
  - `KAFKA_NODE_ID=1`
  - `KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1`
  - `KAFKA_PROCESS_ROLES=broker,controller`
  - `KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1`
  - `KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1`

## Notes

- These values were copied from the workspace metadata

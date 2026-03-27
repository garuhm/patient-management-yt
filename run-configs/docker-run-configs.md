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
- Port bindings:
  - `4000:4000`
- Environment variables:
  - `SPRING_DATASOURCE_PASSWORD=password`
  - `SPRING_DATASOURCE_URL=jdbc:postgresql://patient-service-db:5432/db`
  - `SPRING_DATASOURCE_USERNAME=admin_user`
  - `SPRING_JPA_HIBERNATE_DDL_AUTO=update`
  - `SPRING_SQL_INIT_MODE=always`
  - `BILLING_SERVICE_ADDRESS=billing-service`
  - `BILLING_SERVICE_GRPC_PORT=9001`

## Notes

- These values were copied from the workspace metadata, not from screenshots.

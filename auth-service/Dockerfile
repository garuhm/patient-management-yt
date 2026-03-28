FROM maven:latest AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package

FROM openjdk:25-ea AS runner

WORKDIR  /app

COPY --from=builder ./app/target/auth-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4005

ENTRYPOINT ["java", "-jar", "app.jar"]
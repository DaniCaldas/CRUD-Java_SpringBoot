FROM ubuntu:latest AS BUILD

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=BUILD /app/target/do-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
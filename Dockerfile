FROM ubuntu:latest
LABEL authors="Dev2"

ENTRYPOINT ["top", "-b"]

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8090

COPY --from=build /target/do-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
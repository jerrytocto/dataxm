FROM openjdk:17-jdk-alpine

VOLUME /app

WORKDIR /app

COPY target/dataxm-0.0.1-SNAPSHOT.jar app-java.jar

ENTRYPOINT ["java","-jar","/app/app-java.jar"]
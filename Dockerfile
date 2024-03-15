FROM openjdk:17-jdk-alpine

COPY target/dataxm-0.0.1-SNAPSHOT.jar dataxm.jar

ENTRYPOINT ["java","-jar","dataxm.jar"]
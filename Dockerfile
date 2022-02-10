
FROM openjdk:8-jdk-alpine

COPY target/lab3-0.0.1-SNAPSHOT.war lab3-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java","-jar","/lab3-0.0.1-SNAPSHOT.war"]

FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} jazzers-backend.jar
ENTRYPOINT ["java","-jar","jazzers-backend.jar"]
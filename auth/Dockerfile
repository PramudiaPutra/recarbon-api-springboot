#jdk base image
FROM openjdk:17-slim

WORKDIR /app

COPY target/*.jar auth_test.jar

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app/auth_test.jar"]

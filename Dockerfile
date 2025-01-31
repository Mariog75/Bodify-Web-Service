FROM openjdk:8-jdk-alpine

LABEL maintainer="grebelskimario@gmail.com"

VOLUME /tmp

EXPOSE 8080

# The application's jar file
ARG JAR_FILE

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
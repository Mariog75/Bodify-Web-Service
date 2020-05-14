FROM openjdk:8-jdk-alpine

LABEL maintainer="grebelskimario@gmail.com"

VOLUME /tmp

EXPOSE 8080

# The application's jar file
#ARG target/bodify-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD target/bodify-0.0.1-SNAPSHOT.jar app.jar

#COPY target/bodify-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
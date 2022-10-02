FROM ubuntu:16.04
FROM openjdk:latest as build

WORKDIR /usr/src/app
COPY . .
RUN mkdir /opt/maven3
RUN chmod +x ./build.sh 
RUN ./build.sh
EXPOSE 8080
CMD ["java", "-jar", ".target/Azurras-2.3.0-SNAPSHOT.jar"]
## mvn --version
FROM maven:3.6.3-openjdk-11 AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

## java --version
FROM openjdk:12
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Usuarios.jar
ENTRYPOINT ["java","-jar","/Usuarios.jar"]
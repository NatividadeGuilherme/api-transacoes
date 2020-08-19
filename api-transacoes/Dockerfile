FROM openjdk:8-jdk-alpine
MAINTAINER Guilherme Natividade
COPY target/api-transacoes-2.0.0-SNAPSHOT.jar /var/www/spring-app.jar
WORKDIR /var/www
ENTRYPOINT ["java", "-jar", "spring-app.jar"]

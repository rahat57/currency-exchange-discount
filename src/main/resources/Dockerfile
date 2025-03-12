FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/spring-boot-app-v0.0.1.jar app-v0.0.1.jar
ENTRYPOINT ["java", "-jar", "/app-v0.0.1.jar"]
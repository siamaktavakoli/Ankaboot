FROM openjdk:17-ea-jdk-oracle
MAINTAINER sagorsarker
WORKDIR /app
EXPOSE 8761
COPY ./target/registry-service.jar /app
CMD ["java", "-jar", "registry-service.jar"]

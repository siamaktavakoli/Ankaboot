FROM openjdk:17-ea-jdk-oracle
MAINTAINER sagorsarker
WORKDIR /app
EXPOSE 8888
COPY ./target/config-server.jar /app
CMD ["java", "-jar", "config-server.jar"]

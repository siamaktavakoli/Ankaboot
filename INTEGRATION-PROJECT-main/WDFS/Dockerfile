## Use an official OpenJDK runtime as a parent image
#FROM docker.io/library/openjdk:17-jre
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the JAR file into the container at the specified working directory
#COPY target/final-project-0.0.1-SNAPSHOT.jar /app/app.jar
#
## Expose the port your application will run on
#EXPOSE 8080
#
## Define the command to run your application
#CMD ["java", "-jar", "app.jar"]

# Use an official OpenJDK runtime as a parent image
FROM C:\Users\Dell\.jdks\semeru-17.0.8

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/final-project-0.0.1-SNAPSHOT.jar /app/final-application.jar

# Expose the port your application will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "final-application.jar"]


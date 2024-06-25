# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project JAR file into the container at /app
COPY target/customer-0.0.1-SNAPSHOT.jar /app/customer.jar

# Make the port available to the world outside this container
EXPOSE 8282

# Run the JAR file
ENTRYPOINT ["java", "-jar", "customer.jar"]
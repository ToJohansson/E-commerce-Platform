# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project JAR file into the container at /app
COPY target/order.jar /app/order.jar

# Make the port available to the world outside this container
EXPOSE 8383

# Run the JAR file
ENTRYPOINT ["java", "-jar", "order.jar"]

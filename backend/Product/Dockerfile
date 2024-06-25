# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project JAR file into the container at /app
COPY target/Product-0.0.1-SNAPSHOT.jar /app/product.jar

# Make the port available to the world outside this container
EXPOSE 8181

# Run the JAR file with wait-for-it.sh to ensure the database is ready
ENTRYPOINT [ "java", "-jar", "product.jar"]
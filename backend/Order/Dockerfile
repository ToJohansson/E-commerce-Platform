# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project JAR file into the container at /app
COPY target/order.jar /app/order.jar

# Copy wait-for-it.sh script into the container
COPY scripts/wait-for-it.sh /app/wait-for-it.sh
# Make the script executable
RUN chmod +x /app/wait-for-it.sh

# Make the port available to the world outside this container
EXPOSE 8383

# Run the JAR file with wait-for-it.sh to ensure the database is ready
ENTRYPOINT ["/app/wait-for-it.sh", "order-db:5432", "--", "java", "-jar", "order.jar"]

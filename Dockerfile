# Step 1: Use a smaller base image to run the app
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the pre-built JAR file into the container
COPY target/chat-application.jar /app/chat-application.jar

# Step 4: Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Step 5: Run the application (with the pre-built JAR)
CMD ["java", "-jar", "/app/chat-application.jar"]

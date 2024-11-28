# Step 1: Use OpenJDK image to run Java-based apps
FROM openjdk:17-jdk-slim as builder

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the Maven wrapper and the pom.xml file
COPY pom.xml .

# Step 4: Install Maven and download dependencies (without building the app)
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

# Step 5: Copy the rest of the application files into the container
COPY src /app/src/

# Step 6: Build the application (package it as a JAR file)
RUN mvn clean package -DskipTests

# Step 7: Use a smaller base image to run the app
FROM openjdk:17-jdk-slim

# Step 8: Copy the packaged JAR file from the builder stage
COPY --from=builder /app/target/chat-application.jar /chat-application.jar

# Step 9: Run the application
CMD ["java", "-jar", "/chat-application.jar"]

# Optional: Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

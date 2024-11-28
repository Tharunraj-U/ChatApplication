# Step 1: Use a base image with OpenJDK (Java runtime environment)
FROM openjdk:17-jdk-slim as build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the Maven build file (pom.xml) into the container
COPY pom.xml .

# Step 4: Download the Maven dependencies (without building the whole app yet)
RUN mvn dependency:go-offline -B

# Step 5: Copy the rest of the application files into the container
COPY src /app/src

# Step 6: Build the application (Spring Boot jar file)
RUN mvn clean package -DskipTests

# Step 7: Now, let's create a smaller image with only the required artifacts
FROM openjdk:17-jdk-slim

# Step 8: Set the working directory in the container
WORKDIR /app

# Step 9: Copy the jar file from the build stage
COPY --from=build /app/target/Chat-Application-0.0.1-SNAPSHOT.jar /app/chat-application.jar

# Step 10: Expose the port that the application will run on
EXPOSE 8080

# Step 11: Run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "/app/chat-application.jar"]

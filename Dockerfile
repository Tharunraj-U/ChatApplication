# Step 1: Use an appropriate base image with Java
FROM openjdk:17-jdk-slim as builder

# Step 2: Install Maven
RUN apt-get update && apt-get install -y maven

# Step 3: Copy your pom.xml (and any other necessary files) into the container
COPY pom.xml /app/

# Step 4: Download Maven dependencies offline
WORKDIR /app
RUN mvn dependency:go-offline -B

# Step 5: Copy the rest of your application files
COPY src /app/src/

# Step 6: Build the application (if needed)
RUN mvn clean install -DskipTests

# You can add additional steps like exposing ports or starting your application

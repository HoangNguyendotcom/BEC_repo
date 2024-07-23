# Maven build container
FROM eclipse-temurin:17-jdk AS maven_build
WORKDIR /tmp/

# Copy only the Maven files first
COPY .mvn /tmp/.mvn/
COPY mvnw /tmp/
COPY eclipse-java-formatter.xml /tmp/
COPY pom.xml /tmp/

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the application code
COPY src /tmp/src/

# Build the application
RUN ./mvnw package -Dmaven.test.skip=true

# Pull base image for running
FROM openjdk:17-jdk-slim
EXPOSE 80
ENV JAVA_OPTS="-Xms256m -Xmx2048m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
COPY --from=maven_build /tmp/target/*.jar /app.jar
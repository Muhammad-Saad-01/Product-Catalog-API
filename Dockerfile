#
# Build stage
#
FROM maven:3.8.3-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=production", "-jar", "app.jar"]


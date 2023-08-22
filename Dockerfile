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
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
#COPY --from=build /target/*.jar app.jar
## ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]
#
##
##ADD target/*.jar app.jar
##ENTRYPOINT ["java","-jar", "app.jar"]
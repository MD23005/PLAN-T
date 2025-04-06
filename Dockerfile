<<<<<<< HEAD
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
=======
FROM openjdk:17
COPY target/crud-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT [ "java", "-jar", "java-app.jar" ]    
>>>>>>> 47f108a0e0c4871b83237291b9e2c81477904630

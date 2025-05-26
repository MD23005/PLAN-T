# Etapa de construcción
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Establecer variables de entorno para la conexión a la base de datos
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ep-proud-breeze-a8c1yack-pooler.eastus2.azure.neon.tech/dbPLANT?sslmode=require
ENV SPRING_DATASOURCE_USERNAME=dbPLANT_owner
ENV SPRING_DATASOURCE_PASSWORD=npg_mhEu2sY1RUVj
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_DATASOURCE_HIKARI_CONNECTION_TIMEOUT=20000
ENV SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE=5
ENV SPRING_SQL_INIT_MODE=never
ENV SPRING_JPA_DEFER_DATASOURCE_INITIALIZATION=false
ENV SPRING_JPA_PROPERTIES_HIBERNATE_JDBC_LOB_NON_CONTEXTUAL_CREATION=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_TEMP_USE_JDBC_METADATA_DEFAULTS=false

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
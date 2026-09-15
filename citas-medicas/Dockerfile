# Etapa 1: compilar el proyecto con Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagen final, liviana, solo con el JAR ya compilado
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/citas-medicas-1.0.0.jar app.jar

# Render inyecta la variable PORT; la aplicación debe escuchar en ese puerto
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]

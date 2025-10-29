# Etapa de build
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# Etapa de runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão (apenas documentação)
EXPOSE 8080

# Render define $PORT -> Spring Boot vai usar se configurado
ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -Dserver.address=0.0.0.0 -jar app.jar"]
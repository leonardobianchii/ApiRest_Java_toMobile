# Etapa de build
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
# força o repackage (fat jar com Main-Class)
RUN mvn -q -DskipTests clean package spring-boot:repackage

# Etapa de runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# copia apenas o jar final (evita original-*.jar)
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar
# se preferir fixo:
# COPY --from=build /app/target/mottu-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -Dserver.address=0.0.0.0 -jar app.jar"]

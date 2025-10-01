# build
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app
RUN apk add --no-cache maven
COPY pom.xml .
COPY src src
RUN mvn -q -DskipTests package

# camadas do jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
VOLUME /tmp

ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# (opcional) EXPOSE é só documentação, mas ajuda localmente
EXPOSE 8080

# usuário não-root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# importante: Render define $PORT. Use sh -c para expandir a var.
ENTRYPOINT ["sh","-c","java -Dserver.address=0.0.0.0 -Dserver.port=$PORT -cp app:app/lib/* br.monitoramento.motu.ApiMottuApplication"]

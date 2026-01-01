FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar

ENV PORT=8080
EXPOSE $PORT

RUN addgroup --system --gid 1001 addressapi && \
    adduser --system --uid 1001 --gid 1001 addressapi

USER addressapi

ENTRYPOINT ["java", "-jar", "app.jar"]

FROM gcr.io/distroless/java17

WORKDIR /app

COPY target/crt-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
FROM gradle:jdk21-alpine as build

WORKDIR /app

COPY . .

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre-alpine

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

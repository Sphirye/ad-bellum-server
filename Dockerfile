# Usa una imagen de Gradle con JDK 17 para la fase de construcción
FROM gradle:7.6.0-jdk17-alpine AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia los archivos de código fuente al contenedor
COPY . .

# Ejecuta el comando de Gradle para construir el JAR
RUN ./gradlew bootJar

# Usa una imagen más liviana de JDK para ejecutar la aplicación
FROM eclipse-temurin:17-jre-alpine

# Copia el JAR generado de la fase de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Define el entrypoint para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]

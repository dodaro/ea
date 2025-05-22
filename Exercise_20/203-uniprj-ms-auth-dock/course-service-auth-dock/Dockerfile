# Usa un'immagine base di JDK
FROM eclipse-temurin:17-jdk-alpine

# Crea una cartella per l'app
WORKDIR /app

# Copia il jar (assicurati che esista nella cartella indicata)
COPY target/*.jar app.jar

# Esponi la porta su cui l'app è in ascolto
EXPOSE 8080

# Comando per avviare il jar
ENTRYPOINT ["java", "-jar", "app.jar"]

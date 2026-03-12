# Usa un'immagine base di JDK
FROM eclipse-temurin:17-jdk-alpine

# Crea una cartella per l'app
WORKDIR /app

# Installa curl usando apk (gestore pacchetti Alpine)
RUN apk add --no-cache curl

# Copia il jar (assicurati che esista nella cartella indicata)
COPY target/*.jar app.jar

# Esponi la porta corretta (8088 come nel tuo application.yml)
EXPOSE 8088

# Comando per avviare il jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Usa imagem oficial do Java
FROM openjdk:17-jdk-slim

# Define diretório de trabalho
WORKDIR /app

# Copia o projeto
COPY . .

# Garante permissão do Maven wrapper (se existir)
RUN chmod +x mvnw

# Build do projeto
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Expõe porta
EXPOSE 8080

# Rodar o jar gerado
CMD ["sh", "-c", "java -jar target/*.jar"]
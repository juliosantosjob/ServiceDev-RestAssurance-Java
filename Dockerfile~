# Usar a imagem oficial do Maven com Java 11
FROM maven:3.8.5-openjdk-11-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo POM e o código-fonte para o container
COPY pom.xml .
COPY src ./src

# Fazer o download das dependências
RUN mvn dependency:go-offline -B

# Construir o projeto e rodar os testes
CMD ["mvn", "clean", "test"]
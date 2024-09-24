FROM maven:3.8.5-openjdk-11-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline -B

CMD ["mvn", "clean", "test"]
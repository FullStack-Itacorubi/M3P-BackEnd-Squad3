FROM maven:3.8.7-eclipse-temurin-19-alpine
WORKDIR /app
COPY . .
RUN mvn package -Dmaven.test.skip

EXPOSE 8080

CMD ["java", "-jar", "target/M3PF-BackEnd-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.8-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true

FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
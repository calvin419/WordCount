# Build
FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml .
COPY src ./src
RUN mvn -f pom.xml clean package
RUN mvn -f pom.xml clean install

# Package
FROM openjdk:11
COPY --from=build ./target/PostalProject-1.0-SNAPSHOT.jar /usr/app/PostalProject-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-cp", "/usr/app/PostalProject-1.0-SNAPSHOT.jar", "Solution"]
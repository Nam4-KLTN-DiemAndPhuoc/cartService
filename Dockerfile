# FROM maven:3.6.0-jdk-11-slim AS build
# COPY src /app/src
# COPY pom.xml /app
# RUN mvn -f /app/pom.xml clean package

# FROM openjdk:11
# COPY --from=build /app/target/CartService-0.0.1-SNAPSHOT.jar CartService-0.0.1-SNAPSHOT.jar
# EXPOSE 8085
# ENTRYPOINT [ "java","-jar","CartService-0.0.1-SNAPSHOT.jar" ]

FROM openjdk:11 
ADD /target/CartService-0.0.1-SNAPSHOT.jar CartService-0.0.1-SNAPSHOT.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","CartService-0.0.1-SNAPSHOT.jar"]
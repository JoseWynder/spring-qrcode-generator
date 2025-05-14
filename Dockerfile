FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# I used a .env file to load the Azure environment variables(account name, account key and container name).
#ARG AZURE_STORAGE_ACCOUNT_NAME
#ARG AZURE_STORAGE_ACCOUNT_KEY
#ARG AZURE_STORAGE_CONTAINER_NAME
#
#ENV AZURE_STORAGE_ACCOUNT_NAME=${AZURE_STORAGE_ACCOUNT_NAME}
#ENV AZURE_STORAGE_ACCOUNT_KEY=${AZURE_STORAGE_ACCOUNT_KEY}
#ENV AZURE_STORAGE_CONTAINER_NAME=${AZURE_STORAGE_CONTAINER_NAME}

ENTRYPOINT ["java", "-jar", "app.jar"]
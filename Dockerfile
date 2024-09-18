FROM openjdk:17
RUN mkdir -p /app
WORKDIR /app
copy bitcoin-service/ .
RUN mvn clean install
EXPOSE 8080
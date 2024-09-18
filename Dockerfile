FROM openjdk:17
RUN mkdir -p /app
WORKDIR /app
RUN echo "Current working directory is $(pwd)" \
copy ../bitcoin-service/ ./ \
copy bootstrap/target/bootstrap-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD [ "java", "-jar", "./app.jar" ]
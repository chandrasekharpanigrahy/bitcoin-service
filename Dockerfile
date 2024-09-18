FROM openjdk:17
RUN mkdir -p /app
WORKDIR /app
RUN echo "Current working directory is $(pwd)" \
copy bootstrap/target/bootstrap-0.0.1-SNAPSHOT.jar /app/app.jar \
RUN chmod 777 /app/app.jar
EXPOSE 8080
CMD [ "java", "-jar", "/app/app.jar" ]
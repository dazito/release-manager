FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine
VOLUME /tmp
ADD app.jar app.jar
# RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
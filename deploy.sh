mvn clean install && cd docker && cp ../target/releasemanager-0.0.1-SNAPSHOT.jar app.jar && sudo docker build -t releasemanager . && sudo docker run -d -p8080:8080 releasemanager && cd ..
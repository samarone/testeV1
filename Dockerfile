FROM openjdk:8-jdk-alpine
LABEL maintainer="samarone@gmail.com"
VOLUME /tmp
COPY target/testeV1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 8080

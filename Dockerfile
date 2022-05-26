FROM openjdk:11
MAINTAINER baeldung.com
COPY target/challenge-0.0.1-SNAPSHOT.jar challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/challenge-0.0.1-SNAPSHOT.jar","--server.port=8080"]
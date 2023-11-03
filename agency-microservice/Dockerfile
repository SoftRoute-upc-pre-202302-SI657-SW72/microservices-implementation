FROM openjdk:17-alpine
COPY ./target/agency-microservice-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch /agency-microservice-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/agency-microservice-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /agency-microservice-0.0.1-SNAPSHOT.jar
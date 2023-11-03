FROM openjdk:17-alpine
COPY ./target/enterprise-microservice-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch /enterprise-microservice-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/enterprise-microservice-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /enterprise-microservice-0.0.1-SNAPSHOT.jar
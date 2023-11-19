FROM  openjdk:17-alpine
COPY ./target/client-microservice-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch client-microservice-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","client-microservice-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /client-microservice-0.0.1-SNAPSHOT.jar
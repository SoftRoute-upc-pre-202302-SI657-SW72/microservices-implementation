FROM openjdk:17-alpine
COPY ./target/shipment-microservice-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch /shipment-microservice-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/shipment-microservice-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /shipment-microservice-0.0.1-SNAPSHOT.jar
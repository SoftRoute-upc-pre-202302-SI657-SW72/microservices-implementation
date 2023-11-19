FROM  openjdk:17-alpine
COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch api-gateway-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /api-gateway-0.0.1-SNAPSHOT.jar
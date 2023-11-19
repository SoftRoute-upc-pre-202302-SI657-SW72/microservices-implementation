FROM openjdk:17-alpine
COPY ./target/shipping-register-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch /shipping-register-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/shipping-register-service-0.0.1-SNAPSHOT.jar"]
RUN chm
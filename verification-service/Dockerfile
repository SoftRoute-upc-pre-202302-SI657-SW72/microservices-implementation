FROM openjdk:17-alpine
COPY ./target/verification-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch /verification-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/verification-service-0.0.1-SNAPSHOT.jar"]
RUN chm
FROM openjdk:17-alpine
COPY ./target/feedback-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch /feedback-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/feedback-service-0.0.1-SNAPSHOT.jar"]
RUN chm
FROM openjdk:17-alpine
COPY ./target/docker-spring-boot.jar /
RUN sh -c 'touch /docker-spring-boot.jar'
ENTRYPOINT ["java","-jar","/docker-spring-boot.jar"]
RUN chmod +x /docker-spring-boot.jar
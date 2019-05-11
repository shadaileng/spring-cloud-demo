FROM openjdk:8-jre

WORKDIR /usr/local/app

ENV DOCKERIZE_VERSION v0.6.1

ENV APP_VERSION 0.0.1-SNAPSHOT

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY target/spring-cloud-demo-eureka-$APP_VERSION.jar /usr/local/app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/app/app.jar"]

EXPOSE 8761

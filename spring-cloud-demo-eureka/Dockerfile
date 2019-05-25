FROM openjdk:8-jre

WORKDIR /usr/local/app

ENV DOCKERIZE_VERSION v0.6.1

ENV APP_VERSION 0.0.1-SNAPSHOT

ENV APP_FLAG spring-cloud-demo-eureka

# RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
COPY dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz /tmp/dockerize.tar.gz
RUN tar -C /usr/local/bin -xzvf /tmp/dockerize.tar.gz \
    && rm /tmp/dockerize.tar.gz

COPY target/$APP_FLAG-$APP_VERSION.jar /usr/local/app/app.jar

ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://10.254.198.150:8888/eureka/prod/master", "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-Xms64m", "-Xmx128m", "-Xss256k", "-Djava.compiler=NONE", "/usr/local/app/app.jar", "--spring.profiles.active=prod"]
# ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8761

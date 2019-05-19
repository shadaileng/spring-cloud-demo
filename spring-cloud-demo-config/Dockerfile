FROM openjdk:8-jre

WORKDIR /usr/local/app

ENV APP_FLAG spring-cloud-demo-config

COPY $APP_FLAG/target/$APP_FLAG-$APP_VERSION.jar /usr/local/app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8888

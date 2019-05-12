FROM openjdk:8-jre

WORKDIR /usr/local/app

COPY target/spring-cloud-demo-config-0.0.1-SNAPSHOT.jar /usr/local/app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8888

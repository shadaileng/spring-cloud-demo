FROM openjdk:8-jre

WORKDIR /usr/local/app

ENV APP_VERSION 0.0.1-SNAPSHOT

ENV APP_FLAG spring-cloud-demo-config

COPY target/$APP_FLAG-$APP_VERSION.jar /usr/local/app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC", "/usr/local/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8888

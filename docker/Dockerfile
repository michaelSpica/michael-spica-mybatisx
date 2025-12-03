# Set basic image.
## Setting up JDK.
FROM openjdk:8-jdk-alpine

## Set Image Creator.
LABEL maintainer="MICHAEL"

## Set up working directory.
WORKDIR /home/test

## Set Time Zone.
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone

## Add directly the jar.
ADD ./*.jar app.jar
#COPY ./*.jar app.jar

## Set mount point.
VOLUME /tmp

## Define open ports.
EXPOSE 9527

## Use environment variables to control profiles.
ENV SPRING_PROFILES_ACTIVE=test

## Start container.
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
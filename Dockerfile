FROM amazoncorretto:17.0.7
MAINTAINER Sachin Goyal <sachin.goyal.se@gmail.com>
VOLUME /opt
ARG SERVICE=service

ADD build/libs/$SERVICE.jar /opt/service/service.jar

ENTRYPOINT ["java","-jar","/opt/service/service.jar"]

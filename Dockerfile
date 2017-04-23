FROM debian:wheezy

MAINTAINER Maxime Guillod <maxime@proteck.ch>

RUN apt-get update
RUN apt-get upgrade -y
RUN apt-get install wget default-jre -y

RUN wget --no-check-certificate https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true
RUN mv MockMock.jar?raw=true MockMock.jar

EXPOSE 8282 25

CMD  java -jar MockMock.jar

FROM openjdk:7

RUN apt-get update && apt-get install -y \
    zip

# Add play
ADD https://downloads.typesafe.com/play/1.2.7.2/play-1.2.7.2.zip /
RUN unzip play-1.2.7.2.zip
RUN echo 'alias play="/play-1.2.7.2/play"' >> ~/.bashrc

WORKDIR /play-app

EXPOSE 9000

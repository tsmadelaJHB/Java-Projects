FROM ubuntu:21.04

RUN apt-get update
RUN apt-get install -y openjdk-11-jre curl

COPY ./ home/uss-copernicus-main/

WORKDIR home/uss-copernicus-main
EXPOSE 5050
CMD ["java", "-jar", "Server/target/Server-1.2-jar-with-dependencies.jar"]


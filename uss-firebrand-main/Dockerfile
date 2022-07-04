FROM openjdk:11

ADD target/worldserver-2.0.0-snapshot-jar-with-dependencies.jar /srv/worldserver-2.0.0-snapshot.jar

WORKDIR /srv

EXPOSE 8080

CMD ["java", "-jar", "worldserver-2.0.0-snapshot.jar", "-p", "5050", "-s", "10", "-o", "1,1", "apiport", "80"]
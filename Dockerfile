FROM openjdk:10-jdk

ADD ./target/movie-0.0.1-SNAPSHOT.jar /usr/app/movie-0.0.1-SNAPSHOT.jar

WORKDIR usr/app

ENTRYPOINT ["java","-jar", "movie-0.0.1-SNAPSHOT.jar"]
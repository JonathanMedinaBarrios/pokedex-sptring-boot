FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8090
ADD ./target/springboot-pokedex-0.0.1-SNAPSHOT.jar  pokedex.jar
ENTRYPOINT ["java","-jar","/pokedex.jar"]
FROM openjdk:11
EXPOSE 8082
ADD target/songs-service-0.0.1.jar songs-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "songs-service-0.0.1.jar", "songs-service-0.0.1.jar"]
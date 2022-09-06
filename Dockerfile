from openjdk:17.0.2
volume /tmp
add todolist-0.0.1-SNAPSHOT.jar docker.jar
expose 8080
entrypoint ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/docker.jar"]
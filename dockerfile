FROM amazoncorretto:11.0.8-alpine
VOLUME /app
ADD /target/serasa-api.jar serasa-api.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom /serasa-api.jar"]
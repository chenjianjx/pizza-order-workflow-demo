FROM amazoncorretto:11-alpine-jdk
COPY build/libs/order-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:12119,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
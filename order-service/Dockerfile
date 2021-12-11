FROM amazoncorretto:11-alpine-jdk
WORKDIR /app
COPY . .
RUN ./gradlew clean build
ENTRYPOINT ["java","-jar","build/libs/order-service-0.0.1-SNAPSHOT.jar"]
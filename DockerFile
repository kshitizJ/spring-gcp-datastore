FROM gradle as BUILD
COPY build.gradle /app/
COPY src /app/src/
WORKDIR /app
RUN gradle build

FROM openjdk:17-alpine
COPY --from=BUILD /app/build/libs/*.jar application.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev,datastore", "/application.jar"]
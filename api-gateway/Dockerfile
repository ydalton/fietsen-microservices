FROM alpine:latest AS build

RUN apk add openjdk17-jdk maven
COPY [".", "/app"]
WORKDIR "/app"
RUN mvn package -DskipTests=true

FROM alpine:latest

RUN apk add openjdk17-jre
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
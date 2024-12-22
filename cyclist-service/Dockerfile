FROM alpine:latest AS build

RUN apk add openjdk17-jdk maven
COPY [".", "/app"]
WORKDIR "/app"
RUN mvn dependency:resolve -B
RUN mvn package -B

FROM alpine:latest

RUN apk add openjdk17-jre-headless
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]

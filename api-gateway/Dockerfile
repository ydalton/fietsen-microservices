FROM alpine:latest AS build

ARG GOOGLE_CLIENT_ID
ARG GOOGLE_CLIENT_SECRET

ENV GOOGLE_CLIENT_ID=$GOOGLE_CLIENT_ID
ENV GOOGLE_CLIENT_SECRET=$GOOGLE_CLIENT_SECRET

RUN apk add openjdk17-jdk maven
COPY [".", "/app"]
WORKDIR "/app"
RUN mvn dependency:resolve -B
RUN mvn package -B -DskipTests=true

FROM alpine:latest

RUN apk add openjdk17-jre-headless
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]

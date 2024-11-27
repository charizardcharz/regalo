# Build
FROM eclipse-temurin:21-jdk AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN chmod +x mvnw
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package

# Package
FROM eclipse-temurin:21-jre-alpine
RUN apk add --no-cache sqlite
ARG JAR_FILE=/usr/app/target/*.jar
RUN mkdir /opt/app
COPY --from=build $JAR_FILE /opt/app/regalo.jar
VOLUME /config
EXPOSE 6969/tcp
ENV SPRING_PROFILES_ACTIVE=docker
ENV SERVER_PORT=6969
CMD ["java","-jar","/opt/app/regalo.jar"]
FROM eclipse-temurin:21-alpine
RUN apk add --no-cache sqlite
RUN mkdir /opt/app
COPY japp.jar /opt/app
CMD ["java", "-jar", "/opt/app/japp.jar"]

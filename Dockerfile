#Multistage

#----- BUILD ------
FROM openjdk:21-jdk-bullseye AS builder

#Create a directory for our application
WORKDIR /app

COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

#---- end of BUILD ------

# target/workshop17-0.0.1-SNAPSHOT.jar
RUN  chmod a+x /app/mvnw
RUN /app/mvnw package -Dmaven.test.skip=true

## Run container
FROM openjdk:21-jdk-bullseye

WORKDIR /app_run

# copy workshop17-0.0.1-SNAPSHOT.jar and pasting as weather.jar
COPY --from=builder /app/target/mock-practice-0.0.1-SNAPSHOT.jar mock-practice.jar
COPY --from=builder /app/src/main/resources/static/todos.txt /app_run/src/main/resources/static/todos.txt
## Run
ENV PORT=8080

EXPOSE ${PORT}

#if curl is nto successful, return number 1
# HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3  CMD curl http://127.0.0.1:${PORT}/healthz || exit 1

ENTRYPOINT SERVER_PORT=${PORT} java -jar mock-practice.jar



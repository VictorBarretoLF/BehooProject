FROM maven:3.8.6-amazoncorretto-17

WORKDIR /api

COPY /.mvn .mvn
COPY /src src
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# só é possível rodar esse comando por conta do H2 que está sendo ultilizado.
RUN mvn install

CMD mvn spring-boot:run
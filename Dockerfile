# syntax=docker/dockerfile:1

# if you build your jar with gradle/maven locally, skip the builder stage and just COPY it.
# otherwise uncomment a builder stage. showing the "copy prebuilt jar" path for simplicity.

FROM gcr.io/distroless/java21-debian12:nonroot
WORKDIR /app
# copy the fat jar from your local build output (adjust path/name)
COPY build/libs/*-all.jar /app/app.jar
# or for maven: COPY target/*-SNAPSHOT.jar /app/app.jar

# sensible defaults; tweak heap with env if needed
ENV JAVA_TOOL_OPTIONS="-XX:+UseZGC -XX:+ZGenerational -XX:MaxRAMPercentage=70"
EXPOSE 8080
USER nonroot:nonroot
ENTRYPOINT ["java","-jar","/app/app.jar"]
FROM oracle/graalvm-ce:19.3.1-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.1-java11 as graalvm # For JDK 11
RUN gu install native-image
RUN native-image --no-server --static -cp build/libs/book-jdbc-*-all.jar
COPY . /home/app/book-jdbc
WORKDIR /home/app/book-jdbc

FROM scratch
EXPOSE 8080
COPY --from=graalvm /home/app/book-jdbc/book-jdbc /app/book-jdbc
ENTRYPOINT ["/app/book-jdbc", "-Djava.library.path=/app"]

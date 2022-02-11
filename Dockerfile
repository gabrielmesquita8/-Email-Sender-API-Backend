FROM arm64v8/openjdk
ADD build/libs/Email_API-0.0.1-SNAPSHOT.jar app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app"]
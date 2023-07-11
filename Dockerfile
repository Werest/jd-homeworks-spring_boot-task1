FROM openjdk:17-jdk-alpine

EXPOSE 8081

ADD target/ROOT.jar ROOT.jar

CMD ["java", "-jar", "ROOT.jar"]
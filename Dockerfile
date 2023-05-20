FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=springbootparse_bot
ENV BOT_TOKEN=6129538899:AAFU76aYLpXUfaW0a-L7gGZtaEpSCCmKRAU
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]
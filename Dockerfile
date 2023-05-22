FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=springbootparse_bot
ENV BOT_TOKEN=6129538899:AAFU76aYLpXUfaW0a-L7gGZtaEpSCCmKRAU
ENV BOT_DB_USERNAME=stb_db_user
ENV BOT_DB_PASSWORD=stb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
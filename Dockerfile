FROM maven:3.6.3-jdk-8 as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:8-jre
COPY --from=build /home/app/target/evolution-of-moths-0.0.1-SNAPSHOT.jar /usr/local/lib/evolution-of-moths-0.0.1-SNAPSHOT.jar

RUN ls /usr/local/lib/

RUN echo "America/Sao_Paulo"  > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

EXPOSE 2000

ENTRYPOINT ["java","-Xms512M -Xmx512M -XX:PermSize=256m -XX:MaxPermSize=256m -Djava.security.egd=file:/dev/./urandom", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/usr/local/lib/evolution-of-moths-0.0.1-SNAPSHOT.jar"]

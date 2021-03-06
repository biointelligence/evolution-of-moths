FROM maven:3.6.3-jdk-8 as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:8-jre
COPY --from=build /home/app/target/evolution-of-moths-1.0.0.jar /usr/local/lib/evolution-of-moths-1.0.0.jar

RUN ls /usr/local/lib/

RUN echo "America/Sao_Paulo" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

EXPOSE 2000

ENTRYPOINT ["java","-XX:MinHeapFreeRatio=20","-XX:MaxHeapFreeRatio=40", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/usr/local/lib/evolution-of-moths-1.0.0.jar"]

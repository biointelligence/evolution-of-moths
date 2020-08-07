FROM maven:3.6.3-jdk-8 as build
LABEL maintainer="Carlos Aiello <aiellosti@gmail.com>, Felipe Côrtes <contatos.cortes@gmail.com>"

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM staticfloat/nginx-certbot:latest

ENV CERTBOT_EMAIL=contatos.cortes@gmail.com \
    ENVSUBST_VARS=FQDN \
    FQDN=evolution-of-moths.com \
    IS_STAGING=1

COPY ./config /etc/nginx/user.conf.d
COPY --from=build /home/app/target/evolution-of-moths-1.0.0.jar /usr/local/lib/evolution-of-moths-1.0.0.jar

RUN apt update -y && \
    apt install wget -y && \
    echo "America/Sao_Paulo" > /etc/timezone && \
    dpkg-reconfigure -f noninteractive tzdata && \
    wget -q https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u265-b01/OpenJDK8U-jre_x64_linux_hotspot_8u265b01.tar.gz && \
    tar xzf OpenJDK8U-jre_x64_linux_hotspot_8u265b01.tar.gz

#ENTRYPOINT [ "/jdk8u265-b01-jre/bin/java -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=40 -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar /usr/local/lib/evolution-of-moths-1.0.0.jar &" ] 


COPY call-services.sh .
RUN chmod +x call-services.sh

ENTRYPOINT [ "./call-services.sh" ]

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]

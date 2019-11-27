FROM openjdk:8-jdk-slim

WORKDIR /opt/k8s-logging

ADD ./target/universal/k8s-logging-*.tgz .

CMD [ "/opt/k8s-logging/bin/k8s-logging" ]

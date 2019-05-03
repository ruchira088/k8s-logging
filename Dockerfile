FROM openjdk:8-jdk

WORKDIR /opt/k8s-logging

COPY target/scala-2.12/k8s-logging-assembly.jar .

ENTRYPOINT ["java"]

CMD ["-jar", "/opt/k8s-logging/k8s-logging-assembly.jar"]

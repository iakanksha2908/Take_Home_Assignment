FROM openjdk:17
EXPOSE 8080
ADD target/springboot-receipt-processor-docker.jar springboot-receipt-processor-docker.jar
ENTRYPOINT [ "java", "-jar", "/springboot-receipt-processor-docker.jar" ]
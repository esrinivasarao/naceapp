FROM openjdk:11
EXPOSE 9400
ADD target/spring-boot-dbclm-nace.jar spring-boot-dbclm-nace.jar
ENTRYPOINT ["java","-jar","/spring-boot-dbclm-nace.jar"] 
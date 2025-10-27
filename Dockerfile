FROM openjdk:25-jdk
COPY . /usr/src/app
WORKDIR /usr/src/app
ENTRYPOINT ["java", "-jar", "./target/virtual-library.jar"]

#syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



#run on the command line when this file is ready  --->  docker build .
# give it a name and tag --> docker tag [img ID] [name for the image]:[tag if needed, default="latest"]

#set image's working directory
#WORKDIR /employeeServiceApp

#get pom.xml file into our image
#COPY pom.xml ./
#install dependecies into the image
#RUN mvn dependency:resolve

#add our source code into the image
#COPY src ./src
#RUN mvn package

#RUN ls

#tell Docker what command we want to run when our image is executed inside a container
#CMD ["mvn", "spring-boot:run"]
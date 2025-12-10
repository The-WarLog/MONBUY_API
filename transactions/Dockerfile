# Build stage
#install maven image
FROM docker.io/library/maven:3.9-eclipse-temurin-21 AS build 
WORKDIR /app

# Copy pom.xml and download dependencies
#whats is copy doing here?
#it copies the pom.xml file from the local machine to the working directory in the container
#where is the working directory?
#the working directory is /app as defined in the previous line
#why do we need to download dependencies?
#we need to download dependencies to ensure that all the required libraries and frameworks are available for the build process
#explain mvn dependency:go-offline -B
#this command tells maven to download all the dependencies specified in the pom.xml file and store them locally so that the build can be done offline
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM docker.io/library/eclipse-temurin:21-jre-alpine
WORKDIR /app
#whats happening here?
#we are switching to a new stage in the dockerfile which uses a smaller base image that only contains the Java runtime environment
#this is done to reduce the size of the final image since we only need the runtime to run the application
#what is --from=build?
#--from=build specifies that we are copying files from the previous build stage named "build
#/app/target/*.jar app.jar -->> whats is this path and *? 
#/app/target/*.jar refers to the location of the built jar file in the build stage
#the * is a wildcard that matches any jar file in the target directory  
#why app.jar?
#we are renaming the copied jar file to app.jar for simplicity and consistency when running the application
#where are we copying to?
#we are copying to the working directory in the runtime stage which is /app
# but ther is no . in the destination?
#in this case, the destination is specified as app.jar which means the jar file will be copied to /app/app.jar
# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar
#in .yaml the port is 8081 why here is 8080?
#the port number can be configured independently in the Dockerfile and the docker-compose.yaml file
#the Dockerfile exposes port 8080 for the application to listen on, while the docker
# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

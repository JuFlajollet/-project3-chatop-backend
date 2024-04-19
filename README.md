## Project3 - Chatôp Backend

Third project of OpenClassrooms Java/Angular Fullstack Training Course.

This project was generated with [Spring Initializr](https://start.spring.io/) using [JDK 17.0.10](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Spring Boot 3.2.4](https://spring.io/projects/spring-boot) and [Maven](https://maven.apache.org/).

## Development server

### Setup

_Requirements:_
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Node](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)
- [Chatôp Frontend App](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

### Configuration

Go to the application.properties file in your local Chatôp Backend project.

To configure your db access, create local environment variables:
- DBUser : Your database user
- DBPassword: The database password for your user
- DBUrl: The path to your database (in the format: {serverIP}:{Port}/{nameOfYourDB} => Example: 192.168.1.1:5001/mydatabase)

## 

Open Api Definition: http://localhost:3001/api/v3/api-docs
SwaggerUI: http://localhost:3001/api/swagger-ui/index.html
FrontEnd: http://localhost:4200/
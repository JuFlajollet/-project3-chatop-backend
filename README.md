## Project3 - Chatôp Backend

Third project of OpenClassrooms Java/Angular Fullstack Training Course.

This project was generated with [Spring Initializr](https://start.spring.io/) using [JDK 17.0.10](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Spring Boot 3.2.4](https://spring.io/projects/spring-boot) and [Maven](https://maven.apache.org/).

## Development server

### Setup

_Requirements:_
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Node](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [MySQL Database](https://dev.mysql.com/doc/mysql-getting-started/en/)
- [Chatôp Frontend App](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)
- An IDE for Java ([Eclipse](https://eclipseide.org/)/[IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)/etc.)

You can follow the README on Châtop Frontend App to set up and launch the frontend. Don't forget to grab the SQL script to generate your DB Schema.

Git clone:
> git clone https://github.com/JuFlajollet/project3-chatop-backend

In my SQL command line (Or you can use a database tool like DBeaver):
> mysql> CREATE DATABASE mydb;
> mysql> USE mydb;
> mysql> SOURCE /path/to/file.sql
> mysql> CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
> mysql> GRANT SELECT, INSERT, UPDATE ON mydb . * TO 'user'@'localhost';

You can replace `mydb` by another database name, and you should replace `user` and `password` by more secure user and password.
These credentials will be used for the backend to connect to the database with only the necessary privileges.
The sql file used as source should be the one you have downloaded earlier from the front end repository.

To run the backend app:
- Launch your preferred IDE and open the folder where you cloned the backend project.
- Follow the configuration section below for values to change in application.properties in the project.
- Check if you have correct JDK version for project and Maven.
- Download dependencies and build project through Maven.
- Run the application (`ChatopBackendApplication.java`).

If you use IntelliJ, these links might help for setup: [1](https://www.jetbrains.com/help/idea/run-java-applications.html),[2](https://www.jetbrains.com/help/idea/maven-support.html) and [3](https://www.jetbrains.com/help/idea/troubleshooting-common-maven-issues.html).

### Configuration

Go to the application.properties file in your local Chatôp Backend project.

#### Database
Replace the value of line `spring.datasource.url` by : `jdbc:mysql://{hostname}:{Port}/{nameOfYourDB}` => Example: `jdbc:mysql://myhost:5001/mydatabase`)
To configure your db access, create local environment variables:
- `DBUser`: Your database user
- `DBPassword`: The database password for your user

#### JWT Token
To configure your jwt token secret key, create local environment variable:
- `JwtSecretKey`: Your JWT Token Secret Key (Min. 256 characters else the encryption won't work)

#### SMTP server
You can change the values of the different properties if you wish to user another smtp server than gmail.
Additional properties may also be necessary.
To configure your smtp access, create local environment variables:
- `SMTPUser`: A valid gmail address you have access to
- `SMTPPassword`: An [app password](https://support.google.com/mail/answer/185833?hl=en) to connect to the account

## How To Connect

Chatôp FrontEnd: http://localhost:4200/
Open Api Definition: http://localhost:3001/api/v3/api-docs
SwaggerUI: http://localhost:3001/api/swagger-ui/index.html
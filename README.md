# Auto General Task and Todo Api

This project builds an API for Auto General as per the Swagger spec published at [this location](https://join.autogeneral.com.au/swagger-ui/?url=/swagger.json)

## Overview

This project is built with Spring Boot 2, Spring Data, Spring Web, Hibernate, the H2 database and Gradle. It was built with the Eclipse IDE Version 2019-03.

The API Swagger file is placed in 'src\main\resources', and a generateApi stage is run which generates client stubs from the contract, placed in 'com.auto.api' package and 'com.auto.model' package. These stubs are used in the controller and service code. A @RestControllerAdvice class 'ExceptionProcessor.java' is created to process the exceptions as per the Swagger contract.

Spring Transactional support is included with '@Transactional' annotation, for the /toDo API. JPA is configured using the 'PersistenceConfiguration.java' class.

Every incoming request is routed through from the controller to the service. There is a hit to the repository in case of the /todo API. Hibernate queries are printed to console for debugging purpose. If an exception is thrown, the advice class is used to handle the exception and print a friendly error message in JSON format, to the REST client output tab (I used Insomnia).

## Deployment
The code is deployed on Apache Tomcat v8.5.0, on an Amazon EC2 instance(Amazon Linux AMI). Tomcat is installed locally, and a security group is created to allow inbound web traffic on port 8080. To start the instance, please call me.

## Build
To build the project as a war file which can be deployed on Tomcat, run the Gradle war task : 'gradlew war'. This will execute the generateApi, compileJava and other stages and package the deployable.
To run a build, run the gradle build command : 'gradlew build'
To run a build after deleting the contents of the previous build, run 'gradlew clean build'.
To run the Application locally, using an IDE like Eclipse or NetBeans, create a new Run Configuration with Application.java as the main class, and launch this configuration.

## Testing
For testing locally, use curl commands, for example: "curl http://localhost:8080/AutoGeneralTask/tasks/validateBrackets?input=%7B%7B%7B%7D%7D%7D"
Important note: The brackets in the query parameter must be URL encoded to conform to standards.
For testing on the AWS instance, use a context-path of "Auto-General-Task", since this is the exploded war folder name within the Tomcat web-apps directory.
For example, "POST http://ec2-3-19-63-143.us-east-2.compute.amazonaws.com:8080/AutoGeneralTask/todo"
You can use the attached Insomnia collection of REST requests, and modify the environment map's "base_url" value

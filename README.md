# README #

This README would provide instructions to the solution up and running.

### What is this repository for? ###
* this Service includes Leagues , Matches , Participants and League Rounds functions.
* Version :1.0

### How do I get set up? ###

##### Summary of set up
1. Change configurations in application.properties file into project as per Configuration Section
2. mvn clean install
3. mvn spring-boot:run

#####Configuration:
 1. Replace connection with yours : ${MYSQL_USER} ,  ${MYSQL_PASSWORD}
 2. Replace email credentials with yours: ${USER_EMAIL} and ${EMAIL_PASSWORD}

** Please consider to change the following when using Gmail server for mailing:

1. Turn of Two factor authentication.
2. Make the account allow less secure apps to enable the demo to be able to send email.


#####How to run tests:
1. You can access Swagger documentation to test end points by accessing http://localhost:8080/swagger-ui.html 
2. Run test cases.
3. DDL and DML are included into project.

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
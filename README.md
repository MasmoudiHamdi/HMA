This application is developed using 

Java Version 8, 
Spring Boot version 2.3.1, 
Spring data, 
Spring Web (API rest), 
Spring Security, 
JUnit 5.5.1, 
AssertJ3.14.0, 
H2 Database   
Hibernate 5.4.17

This application allows the management of a bank, 
A bank can have several customers, each customer can have several accounts and  each account has its balances and overdraft and respects the history of the users below 

User Story 1:
As a bank, I accept the deposit of money from customer to his account, if it's more than 0,01€

User Story 2:
As a bank, i accept  the withdrawal of money from customer account, if he does not use the overdraft

User Story 3:
As a bank, I offer the possibility to my client to consult his account balance

User Story 4,
As a bank, i offer the possibility to my client to consult the transaction history on his account

To expose these services to other micro-services, we did use API Rest 
To secure the application, we used JWT
H2 Database, is used locally to safe data 


for the CI / CD, we used openshift with gradle and Nexus 3.

Pipeline definition

The project will go to several steps:
1.Build: jars
2.Tests
3.Build docker java redhat s2i image
4.Deploy in dev
5.Deploy in staging
6.Wait for approval
7.Deploy in prod

Make sure you have Java SE downloaded (& Java 8 or above also required), 

**** To run Projects

In the command line:

  gradle build
  
**** To run the tests:

In the command line:

  gradle cleanTest test
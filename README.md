# Gym Registration API

This API provides endpoints for managing gym member status, registrations, and related activities.

## Overview

This API allows gym admin to perform various operations related to member, trainer, gym class, and registration, such as:
- Register Member or Trainer : Register new member or trainer to the gym.
- Manage Member : View, update, or delete member information.
- Manage Trainer : View, update, or delete trainer information.
- Manage Gym Class : Add, update, or remove gym classes.
- Register for Class : Allow member to register for classes.

Kindly check the documentation listed below.
- Postman documentation: https://documenter.getpostman.com/view/32334876/2sA35Eb465

## Technology
This project is created with:
- Java 17
- PostgreSQL 14.10
- Springboot 3.2.4
- Maven 3.9.6
- JPA

## Installation
Follow these steps to run the Gym Registration API project locally:
1. Clone the repository.
```shell
https://github.com/bharaaa/gym_registration_api.git
```
2. Open the project in your IDE.,e.g., Visual Studio Code, IntelliJ IDEA.
3. Connect to your PostgreSQL database, you can adjust the configuration in application.properties file.
4. Once the application is running, you can access the API using a tool like Postman.
5. The base URL for accessing the API is http://localhost:8080/api/.
# Prog's Apps - Spring Keycloak Integration with Angular

This is an example how to integrate Keycloak with Spring Boot application. 
> **Note**: Requirement is to retrieve the token via backend application instead of directly calling getting it by the Angular frontend.

## Overview
![Documentation overview](.assets/documentation-overview.png)

## Start guide
1. Build using maven:
```
mvn clean install
```
2. Build docker images
```
docker-compose build
```
3. Run containers (`-d` for detached):
```
docker-compose up -d
```
4. Open application under: http://localhost:8081
5. When sign-in page shows up enter (username: *progsapps*, password: *password*)

   ![Keycloak login page](.assets/keycloak-login-page.png)
6. Page shows up

   ![Page](.assets/page.png)

## Communication diagram
1. Get the configuration of Keycloak from the backend 
2. Redirect to the authentication page of Keycloak
3. Get the token via the backend
4. Get data using access token
![OAuth2 communication](.assets/documentation-oauth2-communication.png)

## 


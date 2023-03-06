FROM openjdk:11
COPY progs-apps-spring-keycloak-integration-*.jar /progs-apps-spring-keycloak-integration.jar
ENTRYPOINT ["java","-Dspring.security.oauth2.resourceserver.jwt.issuer-uri=http://keycloak:8080/realms/ProgsApps","-Dspring.security.oauth2.client.provider.keycloak.issuer-uri=http://keycloak:8080/realms/ProgsApps","-jar","/progs-apps-spring-keycloak-integration.jar"]

FROM quay.io/keycloak/keycloak:18.0.0
COPY realm-export.json  /tmp/realm-export.json

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

RUN /opt/keycloak/bin/kc.sh import --file /tmp/realm-export.json

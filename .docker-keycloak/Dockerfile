FROM quay.io/keycloak/keycloak:18.0.0
COPY realm.json  /tmp/realm.json

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

RUN /opt/keycloak/bin/kc.sh import --file /tmp/realm.json

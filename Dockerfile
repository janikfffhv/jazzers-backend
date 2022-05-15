FROM quay.io/wildfly/wildfly

RUN /opt/jboss/wildfly/bin/add-user.sh -u admin -p admin --silent

RUN /opt/jboss/wildfly/bin/add-user.sh -a -u user -p user --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-server-config", "standalone-full.xml"]
# TO GENERATE OPEN API SPECIFICATION (OAS) -> 1) SWAP COMMENT WITH ABOVE 2) EXECUTE ONCE 3) SWAP BACK 4) COPY GENERATED FILE
# CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-server-config", "standalone-microprofile.xml"]

ADD /build/libs/jazzers-backend-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
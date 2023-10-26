FROM tomcat:11.0.0-M13-jdk21-temurin

COPY target/erp-webapp-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080
WORKDIR $CATALINA_HOME
CMD [“catalina.sh”, “run”]
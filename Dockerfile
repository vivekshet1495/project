FROM ubuntu
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
ENV JAVA_HOME /usr
EXPOSE 8080
ADD apache-tomcat.tar.gz /opt
COPY target/gamutkart.war /opt/apache-tomcat-10.1.42/webapps/
ENTRYPOINT ["/opt/apache-tomcat-10.1.42/bin/catalina.sh", "run"]

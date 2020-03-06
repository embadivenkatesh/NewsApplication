FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=newsdb
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_URL=jdbc:mysql://localhost:3306/newsdb
ADD ./target/Newsapp-backend-0.0.1-SNAPSHOT.jar /usr/src/Newsapp-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","Newsapp-backend-0.0.1-SNAPSHOT.jar"]

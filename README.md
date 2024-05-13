# primitive-java-todo-app
A Java project for beginners

- run Maven in command line
- develop without Spring or any other framework
- using pure JDBC and Servlet api
- package war file with maven plugin and deploy to tomcat
- separate client view from server application


add servlet dependency and run `nvm `

```
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>       
</dependencies>
```
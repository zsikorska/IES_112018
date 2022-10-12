# Lab 2 - Java at the server-side and the role of application containers

## 2.1 Server-side programming with servlets

A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol.

First download Apache Tomcat from this [website](https://tomcat.apache.org/). I am using Tomcat 9 (Tomcat v9 → core → zip). To run an application script go to `unzipped folder with tomcat -> bin -> startup.bat`.

3 methods to check if the tomcat server is running:
- `curl -I 127.0.0.1:8080`
- http://localhost:8080/ - I personally recommend
- `tail logs/catalina.out`

You may use [manager app](http://localhost:8080/manager), but first you have to add this to the `unzipped folder with tomcat -> conf -> tomcat-users.xml`:
```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="secret" roles="manager-gui,manager-script"/>
```

You may also use Tomcat server integration in the IntelliJ - here is the [tutorial](https://mkyong.com/intellij/intellij-idea-run-debug-web-application-on-tomcat/) how to configure it. It is very convenient.


Create a project from template from Maven Central catalog: [tutorial step-by-step](https://www.tutorialspoint.com/maven/maven_project_templates.htm).

Some data about maven archetype that will be used:
```
archetypeGroupId=org.codehaus.mojo.archetypes
archetypeArtifactId=webapp-javaee7
archetypeVersion=1.1
```

Start a project using Maven Archetype plugin in an interactive mode:
```
mvn archetype:generate
```

Then you will see a list of archetypes, try to use filters (for example write artifactId). If you choose the archetype, you will be asked to write deatails of your project (groupId, artifactId, version, package) and at the end you need to confirm all the data.

You can also do it in an faster way:
```
mvn archetype:generate -DgroupId=com.mywebapp -DartifactId=web-app -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=webapp-javaee7 -DarchetypeVersion=1.1 -DinteractiveMode=false
```
To build a project use `mvn install`. You can see an error - in the `pom.xml` file change the version of `maven-war-plugin` to 3.2.0. Upload `.war` file from `project folder -> target` into tomcat manager app. Check if everything works :)

How to write basic servlet [tutorial](https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/#webservlet_annotation).\
Tutorial about [form data in servlets](https://www.tutorialspoint.com/servlets/servlets-form-data.htm).

## 2.2 Server-side programming with embedded servers

Tutorial step-by-step [how to start with embedded Jetty server](https://examples.javacodegeeks.com/enterprise-java/jetty/embedded-jetty-server-example/).

Create the project:
```
mvn archetype:generate -DgroupId=com.embeddedjetty -DartifactId=embeddedjetty -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

In `pom.xml` file add these dependencies:
```
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-server</artifactId>
    <version>9.2.15.v20160210</version>
</dependency>

<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-servlet</artifactId>
    <version>9.2.15.v20160210</version>
</dependency>
```

After doing steps from the tutorial, I can type `http://localhost:8680/?username=zuzia` in the website to see the effects (the server runs on port 8680).



## 2.3 Introduction to web apps with a full-featured framework (Spring Boot)


## 2.4 Wrapping-up & integrating concepts


## Review questions

#### A. What are the responsibilities/services of a “servlet container”?

#### B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

#### C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?

#### D. Which annotations are transitively included in the @SpringBootApplication?

#### E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words.

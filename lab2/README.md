# Lab 2 - Java at the server-side and the role of application containers

## 2.1 Server-side programming with servlets

A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol.

First download Apache Tomcat from this [website](https://tomcat.apache.org/). I am using Tomcat 9 (Tomcat v9 → core → zip). To run an application script go to `unzipped folder with tomcat -> bin -> startup.bat`.

3 methods to check if the tomcat server is running:
- `curl -I 127.0.0.1:8080`
- http://localhost:8080/
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

After doing steps from the tutorial, I can type `http://localhost:8680/?username=zuzia` in the browser to see the effects (the server runs on port 8680).



## 2.3 Introduction to web apps with a full-featured framework (Spring Boot)

Very useful tool to start the spring boot project (it can also be done from ide) - [spring initializr](https://start.spring.io/)

Necessary add `Spring Web` dependency.

To run a project using maven wrapper:
```
./mvnw spring-boot:run
```

Then access the browser to see the result: http://localhost:8080/ (or other port, 8080 is the default port)


### Project "spring-mvc":

Serving Web Content with Spring MVC [guide](https://spring.io/guides/gs/serving-web-content/)

Added dependencies: Spring Web, Thymeleaf, and Spring Boot DevTools.

To change a port add this in `application.properties`:
```
server.port=9000
```

Controller class:
```
@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
```

`@GetMapping` annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.

`@RequestParam` binds the value of the query string parameter name into the name parameter of the greeting() method. This query string parameter is not required. If it is absent in the request, the defaultValue of World is used. The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.


To build a home page create a file in `src/main/resources/static` called `index.html`.

To see the results type in browser: http://localhost:9010/greeting

### Project "rest-service":

Building a RESTful Web Service [guide](https://spring.io/guides/gs/rest-service/)

Added dependencies: Spring Web

Controller:
```
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
```

To see the results type in browser: http://localhost:9020/greeting?name=User

Also you can access the endpoint from the command line:
```
curl -v http://localhost:9020/greeting?name=User
```


## 2.4 Wrapping-up & integrating concepts

I used configuration properties to load the data about shows and quotes.

Annotation `@ConfigurationProperties` above the class provides loading configuration to the class automatically. This class has to have getters and setters for the collection where the objects should be loaded. Class model has also to have getters, setters to the fields and constructor without parameters. 

Configuration is made in configuration file `application.properties`. Small piece of it:
```
app.shows[0].id=0
app.shows[0].name=Godfather
app.quotes[0].quote=I'm gonna make him an offer he can't refuse.
app.quotes[0].show=Godfather
app.quotes[1].quote=Leave the gun. Take the cannoli.
app.quotes[1].show=Godfather
```
- app - prefix, same as in `@ConfigurationProperties("app")` annotation
- shows/quotes - name of the collection where the objects will be located
- [nr] - numeber of index in the collection
- .name - name of the field in the class where the new value will be injected


To see the results type in browser:
- http://localhost:9000/ - starter page
- http://localhost:9000/quote - generates random quote
- http://localhost:9000/shows - shows all available movies and shows (which have at least one quote)
- http://localhost:9000/quotes?show=1 - generates random quote from a choosen movie or show


Example of results:
- http://localhost:9000/shows :
```
[{"id":0,"name":"Godfather"},{"id":1,"name":"La casa de papel"},{"id":2,"name":"Forrest gump"},{"id":3,"name":"Stranger things"}]
```
- http://localhost:9000/quotes?show=1 :
```
{"quote":"After all, love is a good reason for all things to fail.","show":"La casa de papel"}
```



## Review questions

#### A. What are the responsibilities/services of a “servlet container”?
The servlet container provides the servlet with easy access to properties of the HTTP request (for example headers and parameters). When a servlet is called, the Web server passes the HTTP request to the servlet container. As the result the servlet container passes the request to the servlet.

To manage the servlet the servlet container can perform these actions:
- create an instance of the servlet and initialize it with its init() method
- constructs a request object to pass to the servlet, it can include:
    - any HTTP headers from the client
    - parameters and values passed from the client
    - complete URI of the servlet request
- constructs a response object for the servlet
- invokes the servlet service() method (this method dispatches requests to the servlet doGet() or doPost() methods)
- calls the destroy() method of the servlet to discard it

#### B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

3 parts of app in MVC Framework in Spring Boot:
- model - contains app data
- view - display the data from model and provide HTML content
- controller - has annotation @Controller, handles HTTP requests, creates a suitable model and returns a proper view


#### C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?

Spring Boot "starters" dependencies help us with managing the dependencies in the project. They are dependency descriptors, each of the them includes some dependency configurations - so instead of adding manually many dependencies, you can add just one starter. Using "starters" increases the productivity and saves time spent on preparation of the configuration.

#### D. Which annotations are transitively included in the @SpringBootApplication?

The @SpringBootApplication annotation includes these annotations:
- @EnableAutoConfiguration - enables Spring Boot’s auto-configuration mechanism
- @ComponentScan - enable @Component scan on the package where the application is located
- @SpringBootConfiguration - enable registration of extra beans in the context or the import of additional configuration classes

#### E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words.

1. Use JSON as the Format for Sending and Receiving Data:\
JSON (JavaScript Object Notation) has become the most popular format for exchanging API data (sending and receiving). In many programming languages there is a strong support for JSON format - it is easy to parse json data and to make some actions on them.

2. Use Nouns Instead of Verbs in Endpoints:\
HTTP methods have the form of the verbs. It would be very confusing if the endpoints would have also the same form. Much more meaningful is using a noun form for endpoints - it exactly says what kind of data will be handling.

3. Use Status Codes in Error Handling:\
Using regular HTTP status codes increases the clarity and helps users understand what's happening with the app. It saves developers time, because they don't have to spend it on identifying the issue - they can faster fix the bug.

4. Maintain good security practices:\
Using SSL/TLS is necessary, because communication between server and client should be private. Sometimes we want to send some private information and we require that our data will be rightly secured. There should be also used least privilege rule - everyone should have minimal access to the information.

5. Use Filtering, Sorting, and Pagination to Retrieve the Data Requested:\
We should avoid trying to retrieve data from the large database, because it might have a big impact on the performance and much slow down the execution of the program. Filtering, sorting, and pagination reduce usage of server resources, because only neccessary data will be used. It can significantly increase the performance.

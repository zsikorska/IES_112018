# Lab 1 - Team practices for enterprise Java development

## 1.1 Basic setup for Java development
*For Windows 10*

### Java JDK setup

1. Install [OpenJDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html "Oracle website")

2. Set `JAVA_HOME`
    1. Open *Search* and type *advanced system settings*.

    2. In the shown options, select the *View advanced system settings* link.
    3. Under the *Advanced* tab, click *Environment Variables*.
    4. In the *System variables* section, click *New* (or *User variables* for single user setting).
    5. Set `JAVA_HOME` as the *Variable name* and the path to the JDK installation as the *Variable value* and click *OK*.
    6. Click *OK* and click *Apply* to apply the changes.

3. Verify the JDK version in your system\
`javac -version`

### [Maven setup](https://maven.apache.org/install.html)

1. Download [Apache Maven 3.8.6](https://maven.apache.org/download.cgi)

2. Unzip apache-maven-3.8.6-bin.zip
3. Add the `bin` directory of the created directory `apache-maven-3.8.6` to the `PATH` environment variable
4. Verify the Maven version in your system\
`mvn -version`

### [Git setup](https://www.atlassian.com/git/tutorials/install-git)

Make sure that you have git installation available in the command line in your development machine.\
```
git --version
git config --list
```
You should get an output from git config including your name and email address

## 1.2 Build management with the Maven tool
Maven goals:
- Making the build process easy
- Providing a uniform build system
- Providing quality project information
- Encouraging better development practices

Naming conventions:\
**groupId** uniquely identifies your project across all projects. A group ID should follow Java's package name rules. This means it starts with a reversed domain name you control. For example,
org.apache.maven, org.apache.commons

**artifactId** is the name of the jar without version. If you created it, then you can choose whatever name you want with lowercase letters and no strange symbols. If it's a third party jar, you have to take the name of the jar as it's distributed.
eg. maven, commons-math

**version** if you distribute it, then you can choose any typical version with numbers and dots (1.0, 1.1, 1.0.1, ...). If it's a third party artifact, you have to use their version number whatever it is, and as strange as it can look. For example,
2.0, 2.0.1, 1.3.1

Checking the version of Maven:
```
mvn --version
```

Creating a project:
```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Build a project:
```
mvn package
```

Adding retrofit and gson dependencies in the project's pom file:
```
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>retrofit</artifactId>
    <version>2.3.0</version>
</dependency>  
<dependency>  
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>converter-gson</artifactId>
    <version>2.3.0</version>
</dependency>
```

Executing a project without/with the city code passed as argument in the command line:
```
mvn exec:java -D"exec.mainClass=com.myweatherradar.WeatherStarter"
mvn exec:java -D"exec.mainClass=com.myweatherradar.WeatherStarter" -D"exec.args=1070500"
```

## 1.3 Source code management using Git
`.gitignore file` - exclude files from being
tracked with Git

Small cheatsheet with some git commands:
- git init - turn an existing directory into a git repository
- git clone [url] - clone (download) an existing remote repository
- git push - uploads all local branch commits to remote repo
- git pull - updates your current local working branch with all new
commits from the corresponding remote branch
- git log - lists version history for the current branch
- git add [file] - snapshots the file in preparation for versioning
- git commit -m "[descriptive message]" - records file snapshots permanently in version history
- git fetch - downloads all history from the remote tracking branches
- git merge - combines remote tracking branch into current local branch

Loggers:\
Logging is a powerful aid for understanding and debugging program's run-time behavior. Logs capture and persist the important data and make it available for analysis at any point in time.

Enabling logging inside the project follows three common steps:
- Adding needed libraries
    ```
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.6.1</version>
        </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.6.1</version>
    </dependency>
    ```
- Configuration - preparing log4j2.xml file which should be placed in `src\main\resources`
- Placing log statements
    ```
    import org.apache.logging.log4j.Logger;
    import org.apache.logging.log4j.LogManager;
    ```
    ```
    private static final Logger logger = LogManager.getLogger(WeatherStarter.class);
    ```
    ```
    logger.info(...);
    ```

## 1.4 Introduction to Docker 
Docker provides the ability to package and run an application in a loosely isolated environment called a container.

`Dockerfile` is simply a text-based script of instructions that is used to create a container image.

Build the container image:
```
docker build -t getting-started .
```
- -t flag tags the image
- . at the end of the docker build command tells Docker that it should look for the Dockerfile in the current directory

Start the container:
```
docker run -dp 3000:3000 getting-started
```
Used flags:
- -d - Run the container in detached mode (in the background).
- -p 3000:3000 - Map port 3000 of the host to port 3000 in the container.
- getting-started - Specify the image to use.

Docker and PostgreSQL:
1. Create Dockerfile and prepare scripts
2. Build an image using this docker file
    ```
    docker image build -t postgresbasic .
    ```
3. Run the container
    ```
    docker run --restart=always --name pg-docker-basic -e PGDATA=/tmp -d -p 5432:5432 -v ${PWD}:/var/lib/postgresql/data postgresbasic
    ```

Some useful docker compose commands:
- docker compose up - run the application
- docker compose down - stop the app
- docker image ls - list local images
- docker inspect *image name* - inspect the image
- docker compose run - run one-off commands for your services
- docker compose --help - show possible commands


## 1.5 Wrapping-up & integrating concepts
Generating two separated projects:
```
mvn archetype:generate -DgroupId=com.ipmaapiclient -DartifactId=IpmaApiClient -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.weatherradar -DartifactId=WeatherForecastByCity -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Make a jar file with compiled code of the IpmaApiClient project:
```
mvn package
```

Add the dependancy in the WeatherForecastByCity project's pom file:
```
    <dependency>
      <groupId>com.ipmaapiclient</groupId>
      <artifactId>IpmaApiClient</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
```

Command used to install the jar file:
``` 
mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=<packaging>

mvn install:install-file -D"file=E:\STUDIA\erasmus_zajecia\ies\IES_112018\lab1\lab1_5\IpmaApiClient\target\IpmaApiClient-1.0-SNAPSHOT.jar" -D"groupId=com.ipmaapiclient" -D"artifactId=IpmaApiClient" -D"version=1.0-SNAPSHOT" -D"packaging=jar"
```

Command used to run the WeatherForecastByCity project for a city with the city code=1070500:
```
mvn exec:java -D"exec.mainClass=com.weatherradar.WeatherStarter" -D"exec.args=1070500" 
```


## Review questions

#### A) Maven has three lifecycles: clean, site and default. Explain the main phases in the default lifecycle.
- **validate**: validate the project is correct and all necessary information is available
- **compile**: compile the source code of the project
- **test**: test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
- **package**: take the compiled code and package it in its distributable format, such as a JAR.
- **integration-test**: process and deploy the package if necessary into an environment where integration tests can be run
- **verify**: run any checks to verify the package is valid and meets quality criteria
- **install**: install the package into the local repository, for use as a dependency in other projects locally
- **deploy**: done in an integration or release environment, copies the final - package to the remote repository for sharing with other developers and projects.


#### B) Maven is a build tool; is it appropriate to run your project to?
The aim of using Maven is to build the project. It is also possible to run a project using Maven but there is needed an *Exec Maven Plugin* which enables commands:
- exec:exec - run any program in an separate process
- exec:java - run only java program in the same virtual machine

#### C) What would be a likely sequence of Git commands required to contribute with a new feature to a given project? (i.e., get a fresh copy, develop some increment, post back the added functionality)
1. **git pull** - get all the latest changes from the remote repo
2. **git add .** - add all files that changed to the staging area
3. **git commit -m "*message*"** - commit staged snapshot to the project history
4. **git push** - upload changes to the remote repo


#### D) There are strong opinions on how to write Git commit messages… Find some best practices online and give your own informed recommendations on how to write good commit messages (in a team project).

You may try to finish the sentence below with your commit subject line:\
*If applied, this commit will... *\
If the commit subject line is written properly you shouldn't have any problem to do this.

Good practices regarding the message style:
- Separate subject from body with a blank line
- Do not end the subject line with a period
- Capitalize the subject line and each paragraph
- Use the imperative mood in the subject line
- Wrap lines at 72 characters
- Use the body to explain what and why you have done something. In most cases, you can leave out details about how a change has been made.


Good practices regarding the message content:
- Describe why a change is being made.
- How does it address the issue?
- What effects does the patch have?
- Do not assume the reviewer understands what the original problem was.
- Do not assume the code is self-evident/self-documenting.
- Read the commit message to see if it hints at improved code structure.
- The first commit line is the most important.
- Describe any limitations of the current code.
- Do not include patch set-specific comments.

#### E) Docker automatically prepares the required volume space as you start a container. Why is it important that you take an extra step configuring the volumes for a (production) database?
If the docker container stops, all the data will be removed. In the production it is very important not to lose data. Mapping a local mount point as a data volume to an appropriate path inside the container is the solution for that problem. Even if the docker container stops, all the data will be still stored.


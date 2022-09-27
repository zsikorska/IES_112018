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


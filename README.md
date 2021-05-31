# Java Automation Testing
This is the java automation project where I show my Java automation skills.

The API under testing is the [PetStore API](https://petstore.swagger.io/#/).
In order to run the tests it is required:
* Latest version of [java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) installed
* Latest version of [maven](https://maven.apache.org/download.cgi) installed
* Make sure you have the JAVA_HOME environment variable set (For further information on how to set the JAVA_HOME environment variable, take a look [here](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html))
* Also set the MAVEN_HOME environment variable (more information [here](https://maven.apache.org/install.html))

With all that set, open your terminal and clone this repository by using the command

> `git clone https://github.com/Ibrian93/JavaAutomation.git`

Once the repository has been cloned, make sure to change to the JavaAutomation directory by typing:

> `cd JavaAutomation`

As the project has been built using maven, in order to run the test type the following command

> `mvn clean test`

In case you require a report you could add also the surefire reports by adding the following command:

> `mvn clean test surefire-report:report`


# Microservices Architecture

**How to run the project:**

mvn clean install

    mvn spring-boot:run

If you want to debug use the next line instead and attach a debugger using a Remote JVM Debug configuration

    mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

**Requirements**

    Java 24
    
    Maven 3.8.1
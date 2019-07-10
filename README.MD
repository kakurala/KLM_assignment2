There are two individual projects in the above root directory `flight-meal-management`.

-   flight-meal-management-api ( Spring Boot )
-   flight-meal-management ( Spring Boot + Angular )

**1. light-meal-management-api** 
API should be running on 8080 port before running `flight-meal-management`. In case if it is running on different port then same can be configured in application.yml in klm-server spring-boot module.

To start API navigate to `light-meal-management-api` folder and then execute below command in separate terminal.
 
`mvn spring-boot:run`

Once API project is started successfully then follow the below instructions to start web application.

**2. flight-meal-management**
This project contains two modules springboot and angular together.

>Note : If you are setting up the project for the first time then the below step in klm-web/pom.xml (line number 39 - 44) should not be commented`
```
<execution>
	<id>npm install</id>
	<goals>
		<goal>npm</goal>
	</goals>
</execution>
```
Execute below commands 

`cd flight-meal-management`

` mvn clean install`

Then move to klm-server module

 `cd klm-server`

Then run the project as spring boot project
 `mvn spring-boot:run`

##### OR build and execute jar 
`mvn clean install`

`java -jar klm-server\target\klm-server-0.0.1-SNAPSHOT.jar`

> Note: change the jar name as per the artifact.

Now open the browser and hit http://localhost:8010/

You should see the below page



>Note: First time the set up might take some time, as it has to download node modules and other dependencies. 
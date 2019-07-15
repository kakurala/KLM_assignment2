There are two sub projects in this project.

-   flight-meal-management-api ( Spring Boot )
-   flight-meal-management ( Spring Boot + Angular )

Instructions to run each project
---
**1. light-meal-management-api** 

Open cmd(in windows), shell (in linux) and navigate to `light-meal-management-api` folder and then execute below commands.
 
`mvn clean install`
`mvn spring-boot:run`

**2. flight-meal-management**

This project contains two modules springboot (backend) and angular (UI) together.

>Note : If you are setting up the project for the first time then make sure the below execution step in klm-web/pom.xml should not be commented`
```
<execution>
	<id>npm install</id>
	<goals>
		<goal>npm</goal>
	</goals>
</execution>
```
Now execute below commands to run the project. 

First Navigate to the project folder.
`cd flight-meal-management`

run maven as below to build angular module and Springboot modules.
` mvn clean install`

Now navigate to klm-server module
 `cd klm-server`

Then run the project as spring boot project
 `mvn spring-boot:run`

##### OR  
Run executable jar as below, once mvn clean install is completed. 
`mvn clean install`

`java -jar klm-server\target\klm-server-0.0.1-SNAPSHOT.jar`

> Note: change the jar name as per the artifact.

Now open the browser and hit http://localhost:8010/

You should see the below page


>Note: First time the set up might take some time, as it has to download node modules and other dependencies. 

![alt text](https://github.com/kakurala/KLM_assignment2/blob/master/KLM.PNG)

#### add flight
![alt text](https://github.com/kakurala/KLM_assignment2/blob/master/klm_add.PNG)

#### Add meals
![alt text](https://github.com/kakurala/KLM_assignment2/blob/master/klm_meals.PNG)

#### Delete Flight
![alt text](https://github.com/kakurala/KLM_assignment2/blob/master/klm_delete.PNG)

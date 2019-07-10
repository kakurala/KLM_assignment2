
Project setup instructions

`Note:  Mock project should be running on 8080 port. In case if it is running on different port then same can be configured in application.yml in klm-server spring-boot module`

### Instructions ###

Clone the repositoriy to local PC

`cd <root_dir>`

` mvn clean install`

Then move to klm-server module

 `cd klm-server`

Then run the project as spring boot project
 `mvn spring-boot:run`

### OR build and execute jar ##
`mvn clean install`

`java -jar klm-server\target\klm-server-0.0.1-SNAPSHOT.jar`

> Note: change the jar name as per the artifact.

Now open the browser and hit http://localhost:8010/

You should see the below page


>Note: First time the set up might take some time, as it has to download node modules and other dependencies. 

### MainScreen

http://localhost:8010/#/home

![image](https://github.com/kakurala/klm_assignment/blob/master/klm_screen.png)


### Application statistics Dashboard

http://localhost:8010/#/dashboard

![image1](https://github.com/kakurala/klm_assignment/blob/master/klm_stats.png)



## Other screens

Searchable source abd destination fields

![image2](https://github.com/kakurala/klm_assignment/blob/master/klm_typeahead.png)


Fare details 

![image3](https://github.com/kakurala/klm_assignment/blob/master/klm_fare_details.png)


Loading overlay on Get Fare action

![image4](https://github.com/kakurala/klm_assignment/blob/master/loading_overlay.png)


Async threads name changed as per requirement

![image5](https://github.com/kakurala/klm_assignment/blob/master/async-tasks-thread.PNG)

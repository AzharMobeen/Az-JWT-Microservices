# Az-JWT-Example
### Framework and helping dependencises:
* In this microservice I'm doing `JWT` Authentication. With the help of bellow Dependencies:
    - `Java 11`
    - `Spring Boot`    
    - `Spring Security`
    - `jjwt`
    - `Spring Data JPA`
    - `h2`
    - `Lombook`
    - `Slf4J`
    - `JUnit 5 from spring boot starter`
    - `JaCoCo plugin`
    - `gradle-docker plugin`
   

### How to run
* It's gradle project just simply run bellow command
```
./gradlew clean build bootRun
```
* If you want to run as docker image
```
./gradlew clean build docker
```
* Above command will create docker image and you can run docker image by
```
docker run --name az-jwt-example-container -p 7171:7171 -d az-jwt-example:latest
```
### Rest API Resources
Resource | Description|
---|---|
|localhost:7171/authenticate|It's `POST` request and you need to pass object and you will receive `JWT` in response|
|localhost:7171/hello|It's `GET` request and you need to pass `Authorization` in header and value will be `Bearer {JWT}`|
### Sample Request & Response
##### /authenticate (Request)
```
curl --location --request POST 'localhost:7171/authenticate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=96F829BBFF437E9E2E2EDF04ED25D3E7' \
--data-raw '{
    "userName": "user",
    "password": "password"
}'
```
##### /authenticate (Response)
```
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjAyODM1NTMxLCJpYXQiOjE2MDI3OTk1MzF9.y7LOWJf-GrK4e6soKcJeMappHmxOjHxyMtWfT97iRzw"
}
```
##### /hello (Request)
```
curl --location --request GET 'localhost:7171/hello' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjAyODM1NTMxLCJpYXQiOjE2MDI3OTk1MzF9.y7LOWJf-GrK4e6soKcJeMappHmxOjHxyMtWfT97iRzw' \
--header 'Cookie: JSESSIONID=96F829BBFF437E9E2E2EDF04ED25D3E7'
```
##### /hello (Response)
```
JWT Authorization working fine
```
### HTTP client-server flow Diagram
![http request/response flow diagram](http-request-flow.jpg)

### /hello request flow Diagram (Success Scenario)
![/hello request/response flow](authorization-diagram.jpg)

### Plus Point
* Exception Handling
* Logging
* JUnit 5
* JaCoCo plug in to show TDD coverage 
    - Please open index.html file az-jwt-example/build/jacoco/test/html/index.html after build.
* Dockerfile
* gradle-Docker plugin
* BDD (via Cocumber/Karate)

### Improvement points
* 93% to 100% unit test coverage.
* SonarQube Quality gate check
* Swager

* `Need to handle all the static messages from property file`
* `JWTExpiryValidate should called before fetching user object from DB` 

### Code Coverage report
![Code Coverage report](JaCoCo-Report.png)

### BDD Cucomber report
![Code Coverage report](Karate-report.png)
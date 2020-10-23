## Ur2Lng

## About
REST API to shorten Urls

### Tech Stack:
* [Spring Boot](http://spring.io/projects/spring-boot) for creating the RESTful Web Services
* [MockMVC](https://spring.io/guides/gs/testing-web/) for testing the Web Layer
* [Mockito](https://site.mockito.org/) for testing the Services Layer
* [MySQL](https://www.mysql.com/) as database
* [Gradle](https://gradle.org/) for managing the project's build

## Endpoints
Request Method | URI | Body (JSON) | Description |  
:---: | :--- | :---: | :--- |
GET | http://localhost:8081/ur2lng/{id} | - | Get long url that has been previously created using POST endpoint and redirect |
POST | http://localhost:8081/ur2lng | { "longUrl": "[http...]" } | Create a short Url from a long Url and return short Url resource location in HTTP headers |

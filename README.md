# Spring Boot REST-API Service

Create a simple Rest API which provides a service for storing, updating, retrieving and deleting person entities

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method in the `rest.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Some Endpoints

Here are some endpoints you can call:

#### Create a person resource

```
POST: /api/v1/person
Accept: application/json
Content-Type: application/json

Request Body:
{
    "first_name": "Sabbir",
    "last_name": "Ahmed",
    "age": "28",
    "favourite_colour": "Blue",
    "hobby": [
        "Travelling",
        "Playing Games"
    ]
}

Response:
{
    "message":"Create Successfully."
}
```

#### Update a person resource

```
PUT: /api/v1/person/1
Accept: application/json
Content-Type: application/json

Request Body:
{
    "first_name": "Sabbir",
    "last_name": "Ahmed",
    "age": "28",
    "favourite_colour": "Blue",
    "hobby": [
        "Travelling"
    ]
}

Response:
{
    "message":"Update Successfully."
}
```

#### Delete a person resource

```
DELETE: /api/v1/person/1
Accept: application/json
Content-Type: application/json

Response:
{
    "message":"Delete Successfully."
}
```

#### Retrieve a person resource

```
GET: /api/v1/person/1
Accept: application/json
Content-Type: application/json

Response:
{
    "id": 1,
    "first_name": "Sabbir",
    "last_name": "Ahmed",
    "age": "28",
    "favourite_colour": "Blue",
    "hobby": [
        "Travelling"
    ]
}
```

#### Retrieve list of person resource

```
GET: /api/v1/person
Accept: application/json
Content-Type: application/json

Response:
[
    {
        "id": 1,
        "first_name": "Sabbir",
        "last_name": "Ahmed",
        "age": "28",
        "favourite_colour": "Blue",
        "hobby": [
            "Travelling"
        ]
    }
]
```

## To view Swagger 2 API docs

Run the server and browse to [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
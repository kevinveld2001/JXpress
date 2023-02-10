# JXpress
JXpress is a java based http web server build from scratch. It makes use of the built-in java ServerSocket to communicate with the browser.
The request will be parsed to a request object. It will be passed into the callback that is defined for that route. 
The response returned using the response callback will be sent to the browser.


## Getting started

Below is a small example to get you started. In the [example folder](https://github.com/kevinveld2001/JXpress/tree/master/src/main/java/example) are more examples that show more features.

```java
App app = new App();

app.addRoute(Method.GET, "/", req -> new Response().setHTML("Hallo World"));

app.listen(80);
```

## Features

- [X] Routing
- [X] Header parsing
- [X] Params parsing
- [X] XForm parsing
- [X] Getting raw request body
- [ ] JSON parsing
- [X] Global & local middleware 

- [X] Text response
- [X] HTML response
- [X] JSON stringify response 
- [X] Response Status codes
- [X] Response headers


## Unit tests

Junit is used to make unit test. Most features have unit tests. 
If you like to contribute to this project and make code changes make sure to run the unit test before making a PR.
Unit tests can be found in the [test folder](https://github.com/kevinveld2001/JXpress/tree/master/src/test/java).

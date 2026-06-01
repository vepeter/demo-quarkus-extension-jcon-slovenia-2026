# demo-quarkus-extension-jcon-slovenia-2026

## Description
The repository contains source code which should be developed for the JCon Slovenia 2026 demo.

It consists of 2 parts:
* `demo-extension` - multimodule project which implements the `demo-extension` Quarkus extension
* `demo-app` - a Quarkus based project which uses the `demo-extension` Quarkus extension

## Requirements
The following tools are required for the demo:
* JDK 17+ (the `demo-app` project is configured to use Java 25 but it could be downgraded to 17)

## How to execute
The following steps should be executed to see the extension in action:
1. Build the `demo-extension` project
```shell
cd demo-extension
mvn clean install
```

2. Build and run the `demo-app` project
```shell
cd ../demo-app
mvn clean quarkus:dev
```

3. Verify the extension was used in `demo-app`. Check logs 
The logs should contain the following entry:
```text
026-05-30 09:24:38,977 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, demo-extension, rest, smallrye-context-propagation, vertx]
```
where `demo-extension` is listed.

4. Open the `http://localhost:8080/demo` url. It should respond with the following content on the `GET` method:
```json
{
    "name": "demo-app",
    "artifactId": "demo-app",
    "groupId": "my.demo.jconslovenia2026.quarkus",
    "version": "1.0.0-SNAPSHOT",
    "buildTime": "2026-05-30T09:24:37.764213388+02:00",
    "quarkusVersion": "3.36.0"
}
```
The content above was generated in the `demo-extension` extension during the build time.

5. Add the following property to the `demo-app/src/main/resources/application.properties` file:
```properties
quarkus.demo.path=/demo-info
```

6. Open the `http://localhost:8080/demo-info` url, it should respond with the same content as in the step `4`.
The endpoint path was changed as the result of the property creation.

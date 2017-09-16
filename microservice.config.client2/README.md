## Introduction

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system
It offers 1 central place to manage your configuration
Uses labelled versions of configuration environments, as well as being accessible to a wide range of tooling for managing the configuration

## Clients

When a Spring Cloud Config client starts, it follows a specific bootstrapping sequence.
It binds to the Config Server (via the bootstrap configuration property `spring.cloud.config.uri`) and initializes Spring Environment with remote property sources.

- A client connecting to a config service has a name (spring.application.name in the bootstrap.yml)
- The default address of `spring.cloud.config.uri` is http://localhost:8888
- You can also specify a `spring.cloud.config.label=myversion` property to point to a specific version of the configuration for that particular application.

## Combining with Eureka

Spring Cloud Config also integrates well with Eureka.

- spring.cloud.config.discovery.enabled=true
- eureka.client.serviceUrl.defaultZone

When clients start they need one additional network hop to the Eureka discovery server, and pull in the remote configuration

With this approach, the config server can change its location / port without any impact to the clients.

## Example App

- This example app (name : configclient2) starts a spring boot microservice on port 8200 that will conntect to a spring cloud config server.
- It will fail to start when the config server is down.
- You'll need to start the microservice.config.server app.

when the app starts, you can access a REST endpoint that will fetch a config value

curl http://localhost:8200/value


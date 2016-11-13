## Introduction

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system
Offers 1 central place to manage your configuration
Uses labelled versions of configuration environments, as well as being accessible to a wide range of tooling for managing the configuration


## Clients

When a Spring Cloud Config client starts, it follows a specific bootstrapping sequence.
It binds to the Config Server (via the bootstrap configuration property `spring.cloud.config.uri`) and initializes Spring Environment with remote property sources.

The default address of `spring.cloud.config.uri` is http://localhost:8888

All that clients need to do is
 
  
 
## Combining with Eureka

spring.cloud.config.discovery.enabled=true
eureka.client.serviceUrl.defaultZone

When clients start they need one additional network hop to the Eureka discovery server, and pull in the remote configuration

With this approach, the config server can change its location / port without any impact to the clients.
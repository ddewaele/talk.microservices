A simple Eureka client will allow the service to register itself with a Eureka server

It needs to have Eureka on the classpath and the `@EnableEurekaClient` annotation.

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.2.2.RELEASE</version>
</dependency>
```
		
By default the following config is used by an application that has a `@EnableEurekaClient` annotation

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```      
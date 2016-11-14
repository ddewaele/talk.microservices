A simple Eureka client will allow the service to register itself with a Eureka server

It needs to have Eureka on the classpath and the `@EnableEurekaClient` annotation.

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```
		
By default the following config is used by an application that has a `@EnableEurekaClient` annotation

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```      

So if you run your Eureka client in a standard location you don't need to provide anything.

You do need to give your application a name

```
spring:
  application:
    name: registered-service2
```

If you decide to run your application with a dynamic port, ensure that you also provide dynamic instanceIds. (otherwise all of your services will be registered with `<ip address>:0`

```
server:
  port: 0
eureka:
  instance:
    instanceId: ${spring.application.name}:${vcap.application.instance_id:${spring.application.instance_id:${random.value}}}
```
  
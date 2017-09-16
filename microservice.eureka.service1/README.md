# Eureka Client

## What ?

A simple Eureka client allows 

- its services to register themselves with a Eureka server
- query the registry for services and calls them.

## Spring Initializr

```
curl https://start.spring.io/starter.tgz -d dependencies=web,actuator -d groupId=com.ixortalk -d artifactId=simple.service -d name=simple.service -d language=java -d type=maven-project -d baseDir=simple.service | tar -xzvf -
curl https://start.spring.io/starter.tgz -d dependencies=web,actuator -d groupId=com.ixortalk -d artifactId=simple.client -d name=simple.client -d language=java -d type=maven-project -d baseDir=simple.client | tar -xzvf -
```

To view some meta-data on spring initializr :

```
curl -H 'Accept: application/json' https://start.spring.io | python -m json.tool
```

## How ?

It needs to have Eureka on the classpath and the `@EnableDiscoveryClient` annotation. If Eureka is on the classpath that one will be picked.
The Eureka specific version of this annotation is called @EnableEurekaClient


```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```
		
We'll also add a feign dependency so that we can talk to other services.

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
<dependency>
    <groupId>com.netflix.feign</groupId>
    <artifactId>feign-jackson</artifactId>
    <version>8.18.0</version>
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
  
## Eureka endpoints

Eurek exposes an XML based REST API :

```
curl -v http://localhost:8761/eureka/apps/REGISTERED-SERVICE2
```

## Calling other services (test load balancing)

```
while sleep 1; do curl  http://localhost:8001; echo "";  done
```

  
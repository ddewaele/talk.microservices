

com.netflix.client.ClientException: Load balancer does not have available server for client: registered-service2

If you want feign to be able to talk to the registry and lookup services by name we need the following depndency :

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.1.5.RELEASE</version>
</dependency>
```

And the following annotation 

@EnableDiscoveryClient

The Eureka specific version of this annotation is called @EnableEurekaClient


curl -v http://localhost:8001/application/REGISTERED-SERVICE2 | python -m json.tool

Eurek exposes an XML based REST API :

```
curl -v http://localhost:8761/eureka/apps/REGISTERED-SERVICE2
```


```
while sleep 1; do curl  http://localhost:8001; echo "";  done
```

Other implementations

https://www.consul.io/
https://zookeeper.apache.org/

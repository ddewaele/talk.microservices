Start with a very simple zuul server and 2 simple clients
```
curl https://start.spring.io/starter.tgz -d dependencies=cloud-zuul,cloud-eureka,web,security,actuator -d groupId=com.ixortalk -d artifactId=zuul.server -d name=zuul.server -d language=java -d type=maven-project -d baseDir=zuul.server | tar -xzvf -
curl https://start.spring.io/starter.tgz -d dependencies=web,actuator -d groupId=com.ixortalk -d artifactId=simple.service1 -d name=simple.service1 -d language=java -d type=maven-project -d baseDir=simple.service1 | tar -xzvf -
curl https://start.spring.io/starter.tgz -d dependencies=web,actuator -d groupId=com.ixortalk -d artifactId=simple.service2 -d name=simple.service2 -d language=java -d type=maven-project -d baseDir=simple.service2 | tar -xzvf -


curl https://start.spring.io/starter.tgz -d dependencies=web,actuator -d groupId=com.ixortalk -d artifactId=simple.monitoring1 -d name=simple.monitoring1 -d language=java -d type=maven-project -d baseDir=simple.monitoring1 | tar -xzvf -
```

A very simple config
    
```
server:
  port: 8000

zuul:
  routes:
    user:
      path: /simpleservice1/**
      url: http://localhost:9001/data1
    user-mgmt:
      path: /simpleservice2/**
      url: http://localhost:9002/data2
```      

```
curl -v -u user:cb54ada3-19fe-4302-b111-127665db6189 http://localhost:8000/routes | python -m json.tool

* Connected to localhost (::1) port 8000 (#0)
* Server auth using Basic with user 'user'
> GET /routes HTTP/1.1
> Host: localhost:8000
> Authorization: Basic dXNlcjpjYjU0YWRhMy0xOWZlLTQzMDItYjExMS0xMjc2NjVkYjYxODk=
> User-Agent: curl/7.43.0
> Accept: */*
> 
< HTTP/1.1 200 
< X-Application-Context: application:8000
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Strict-Transport-Security: max-age=31536000 ; includeSubDomains
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Mon, 14 Nov 2016 09:02:41 GMT
< 

{
    "/simpleservice1/**": "http://localhost:9001/data1",
    "/simpleservice2/**": "http://localhost:9002/data2"
}
```
    
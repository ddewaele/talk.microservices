## Introduction

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system
Offers 1 central place to manage your configuration
Uses labelled versions of configuration environments, as well as being accessible to a wide range of tooling for managing the configuration

## Configuration

The config server needs the following configuration.

```
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/ddewaele/spring-cloud-config-repo
          cloneOnStart: true
```

If you want to secure your configuration

```
#          uri: git@github.com:ddewaele/spring-cloud-config-repo-private.git
#          username: ddewaele
#          password: strong_password
```

## REST interface

```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

For example to retrieve the `dev` profile config for our `configclient1` application, we can issue the following call:

```
curl http://localhost:8888/configclient1/dev | python -m json.tool

{
    "label": "master",
    "name": "configclient1",
    "profiles": [
        "dev"
    ],
    "propertySources": [
        {
            "name": "https://github.com/ddewaele/spring-cloud-config-repo/configclient1-dev.yml",
            "source": {
                "config.secret-value": "super strong password",
                "config.value": "a service1-specific config value for dev"
            }
        },
        {
            "name": "https://github.com/ddewaele/spring-cloud-config-repo/configclient1.yml",
            "source": {
                "config.secret-value": "super strong password",
                "config.value": "a service1-specific config value"
            }
        },
        {
            "name": "https://github.com/ddewaele/spring-cloud-config-repo/application.yml",
            "source": {
                "config.super-secret-value": "super secret config value",
                "config.value": "a default value from config"
            }
        }
    ],
    "state": null,
    "version": "2f63a6c4705448d79e73da82bf30b31d8c171354"
}
```

A config server needs to have the following dependency and the `@EnableConfigServer` annotation.

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
    <version>1.2.1.RELEASE</version>
</dependency>
```

Or it can use the starter dependencies


	
## Encrypting / Decrypting

keytool -genkeypair -alias mytestkey -keyalg RSA \
  -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" \
  -keypass changeme -keystore server.jks -storepass letmein

It also exposes the following mappings to encrypt / decrypt sensitive information.			

```
Mapped "{[/encrypt],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.encrypt(java.lang.String,org.springframework.http.MediaType)
Mapped "{[/encrypt/{name}/{profiles}],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.encrypt(java.lang.String,java.lang.String,java.lang.String,org.springframework.http.MediaType)
Mapped "{[/decrypt/{name}/{profiles}],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.decrypt(java.lang.String,java.lang.String,java.lang.String,org.springframework.http.MediaType)
Mapped "{[/decrypt],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.decrypt(java.lang.String,org.springframework.http.MediaType)
Mapped "{[/encrypt/status],methods=[GET]}" onto public java.util.Map<java.lang.String, java.lang.Object> org.springframework.cloud.config.server.encryption.EncryptionController.status()
```

I've setup a simple config repo hee : https://github.com/ddewaele/spring-cloud-config-repo


A config client will have the configService in its environment, as can be seen here when executing `curl  http://localhost:8100/env | python -m json.tool | head -n 10`

```
{
    "applicationConfig: [classpath:/application.yml]": {
        "server.port": 8100
    },
    "configService:configClient": {
        "config.client.version": "a42eeb199defefb94d270c25a4f8a3577b7ded33"
    },
    "configService:https://github.com/ddewaele/spring-cloud-config-repo/application.yml": {
        "config.value": "value_from_config"
    }
```

## Refreshing scope

By calling the refresh endpoint, `@RefreshScope` beans will reload their configuration:
```
curl -X POST http://localhost:8100/refresh
```

So when we update the centralized config, and issue a refresh to the spring-boot app, they will pick up the new config automatically.
 
 
## Encrytping / decrypting
 
### Encrypting

```
curl localhost:8888/encrypt -d "super strong password"
eadaa4e2e765f2ebff88093d6b6dec1bbf885e7f90d64e4b09efd89a8d0d7bae28f48968f6fb46845e1b07714a14ac4f 
```

### Decrypting

```
curl localhost:8888/decrypt -d "eadaa4e2e765f2ebff88093d6b6dec1bbf885e7f90d64e4b09efd89a8d0d7bae28f48968f6fb46845e1b07714a14ac4f" 
super strong password
```


### Store the cipher in a config file

```
config:
   value: 'a service1-specific config value'
   super-secret-value: '{cipher}eadaa4e2e765f2ebff88093d6b6dec1bbf885e7f90d64e4b09efd89a8d0d7bae28f48968f6fb46845e1b07714a14ac4f'
```
 
# References

https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html
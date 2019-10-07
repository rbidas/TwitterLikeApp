# Twitter Like App

## Prerequisites
- installed java 8 or higher

## Build

```
./mvnw clean install
```

## Run
```
./mvnw clean package && java -jar target/twitterApp-1.0.jar
```

or 

```
./mvnw spring-boot:run
```

Default server port: 8080

## Test
All available endpoints can be tested on page [http://localhost:8080/swagger-ui.html]( http://localhost:8080/swagger-ui.html)

### Access to h2 console
---- 
URL: http://localhost:8080/console
JDBC URL: jdbc:h2:mem:example-app
user: sa
password: empty
----

## Descriptions
### Step 1:
Shortcuts. In production mode, we should:

- Add input validations such as “NotNull, NotEmpty, token” etc.
- Add Request/Response DTO.
- Protect endpoints with token. Add user login/logout and extract userId and roles from session context. In SpringBoot we can use Spring Security with external authentication server.
- I used H2 DB. In production application should use regular DB (PostgreSQL, Oracle) or we may consider a Non-SQL such as Cassandra.
- Divide application into separate microservices.
- Tags return only message ID. We should add “search microservice” to combine results from tags and posts endpoints, or join posts and tags on DB layer.
- Add Ribbon or external Load Balancer. 
- Configuration is stored now in application, we need to install config server, use git as source of configuration, or use other solution to provide configuration.
- There are only few unit and integration tests. Next step will be to increase test coverage.

### Step2:
**Deploy.** 
We should use CI/CD (eg Jenkins) to build, run tests and create artefacts (jar or docker image). 
Deploy jar directly on VM, use Docker to run containers or deploy it in cloud (OpenShift, AWS etc)


**Traffic:**
Application must be stateless or if any node need state we should use sticky-session.
We should monitor traffic, and deploy new node if needed.

**Monitoring and Maintaining:**
To provide good quality, application must be monitored. We should use ELK stack (ElasticSearch, logstash, Kibana) to monitor logs. In elasticsearch we can create dashboards with actual system state. We can use OP5/Zabbix to monitor errors and warnings. 
Stack and used technology depends on how we deploy our application. 
Logs stored in one place allow us to handle logs from many microservices.

Dividing application on separate components allows us to:
- deploy independent each component regarding amount of traffic
- deploy only components which were changed
- Increase SLA
- New components  can be add without a restart whole system.

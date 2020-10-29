## Application is available here: (username: example, password: example)
* SIT - https://investment-calculator.ersa-team.sit.fintechchallenge.pl/
* UAT - https://investment-calculator.ersa-team.uat.fintechchallenge.pl/
* PROD - https://investment-calculator.ersa-team.fintechchallenge.pl/


## DESCRIPTION

The backend application for an investment calculator
Dragon investments is a finance calculator that helps users to make decision regarding their saving and investment .
On Dragon investment we prepared a wide range of investment calculation for everyone who is wonder to know that which solution will be the best for them.
Thanks to it, choosing the right path to multiply money will not be so difficult.
Our finance calculator is generally a free application which is updated regularly,
also on this way you’ll get reliable results,and enabling you to choose the optimal product.


### TECHNOLOGIES USED:

-   Java 8
-   Spring
-	Spock + Groovy
-	Maven
-   Liquibase
-   Swagger
-   PostgreSQL


### How to run application locally

First method: 
-	Run postgres database on port 5432
-	docker command : 'docker run -e POSTGRES_PASSWORD=docker -d -p 5432:5432 postgres'
-	Run the application, application by default will be available on port 8080


Second method:
-	In terminal run command : docker-compose up
-	Run the application, application by default will be available on port 8080


### TESTS
If you want to start integration tests you need to have run postgres database on docker.
Instruction how to start docker database is in section above.


### API DOCUMENTATION
API documentation has been created by a swagger tool and available here:
* SIT - https://investment-calculator.ersa-team.sit.fintechchallenge.pl/api/swagger-ui.html
* UAT - https://investment-calculator.ersa-team.uat.fintechchallenge.pl/api/swagger-ui.html
* PROD - https://investment-calculator.ersa-team.fintechchallenge.pl/api/swagger-ui.html


## This is regular application created via spring.io. Have a look at:
* `Jenkinsfile` you'll find here how to build, push and deploy you application.
* `kubernetes.yaml` check IngressRoute to find out how publish your application with DNS name over HTTPS
* expose management port in you app and set readiness and liveness probes
* remember to push docker images to appropriate registry
* to keep registry easy-to-read, prefix your docker image with project name (ie. `ersa-team/investment-calculator`)
* in kubernetes steps use `fintech/kubernetes-agent` agent which contains git, kubectl, helm
* you don't have to specify kubernetes namespace - it's limited to project in which you build (ie. Training apps will be deployed to training namespace only)
* there are two kuberentes configurations available `kubeconfig-sit` and `kubeconfig-sit` (check Jenkinsfile)
* because of using tag `latest` you need to execute `kubectl rollout restart deployment investment-calculator`
* use project as a DNS subdomain, to keep it clear (ie. `investment-calculator.ersa-team.fintechchallenge.pl`)
* protect your ingress with basic auth credentials (using Traefik middleware)
* in order to deploy application to production - use dedicated Jenkins job

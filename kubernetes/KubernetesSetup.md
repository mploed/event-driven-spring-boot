# Event Driven Spring Boot on Kubernetes

In order to get the application up and running on Kubernetes you need to perform the following steps. This has been
tested on the Google Kubernetes Engine.

### 1. Infrastructure

Create MySQL Databases for Customer, Application Process, Credit Application and Credit Decision:

```
kubectl create -f infrastructure/mysql-application-process.yaml

kubectl create -f infrastructure/mysql-credit-application.yaml

kubectl create -f infrastructure/mysql-customer.yaml

kubectl create -f infrastructure/mysql-credit-decision.yaml
```

Create RabbitMq Deployment and Service:

```
kubectl create -f infrastructure/rabbitmq.yaml
```

### 2. Create Services

```
kubectl create -f services/application-process-service.yaml

kubectl create -f services/credit-application-service.yaml

kubectl create -f services/credit-decision-service.yaml

kubectl create -f services/scoring-service.yaml

kubectl create -f services/customer-service.yaml
```


### 3. Create ConfigMaps

Wait for the Services to get public URLs and then look them up in order to create three ConfigMaps:

```
kubectl create configmap application-process-config --from-literal=nextProcessStepUrl=[CREDIT-APPLICATION-URL]/credit-application/

kubectl create configmap credit-application-config --from-literal=nextProcessStepUrl=[CUSTOMER-URL]/customer/

kubectl create configmap customer-config --from-literal=nextProcessStepUrl=http://35.205.109.120:9000/status/ --from-literal=routeToSelf=http://35.195.20.175:9002/
```




### 4. Create Deployments

```
kubectl create -f deployments/credit-decision-deployment.yaml

kubectl create -f deployments/customer-deployment.yaml

kubectl create -f deployments/scoring-deployment.yaml

kubectl create -f deployments/credit-application-deployment.yaml

kubectl create -f deployments/application-process-deployment.yaml
```

## Access to the Application
Lookup the URL + Port of the application-process service and access it.
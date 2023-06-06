# launcher

Service Launcher

# Installation
* Docker: https://docs.docker.com/docker-for-mac/install/
* Virtual Box if you don't already have it: `brew install --cask virtualbox`
* Minikube: `brew install minikube`
* Kubernetes cli tools: `brew install kubernetes-cli`
* [Optional] for cluster visualisation: `brew install derailed/k9s/k9s`

# Deployments (Local Minikube Cluster)

## Create New Cluster
```
minikube config set disk-size 20GB
minikube config set memory 6144
minikube delete
minikube start
```

## Rebuild and Push to Docker Hub
```
./rebuildAndPush.sh
```

## Deploy service Only
```
./redeployService.sh
```

## Deploy service & database
```
./redeployAll.sh
```

## Port forwarding
```
kubectl port-forward service/launcher 8081:8080 --namespace=launcher
```

## API Playground (Open API)
After port forwarding
```
http://localhost:8081/swagger-ui.html
```


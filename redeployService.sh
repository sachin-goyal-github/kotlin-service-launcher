kubectl create namespace launcher --dry-run=client -o yaml | kubectl apply -f -
kubectl delete -f infrastructure/k8s/service-deployment.yml --namespace=launcher
kubectl create -f infrastructure/k8s/service-deployment.yml --namespace=launcher

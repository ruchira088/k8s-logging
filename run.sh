#!/usr/bin/env bash

echo "Starting minikube..."
minikube start --cpus 4 --memory 8192 --disk-size "100g"

minikube addons enable default-storageclass
minikube addons enable storage-provisioner
echo "Successfully started minikube"

echo "Installing the EFK stack"
kubectl apply -f efk/kube-logging.yaml

kubectl apply -f efk/

eval $(minikube docker-env)

sbt clean compile assembly

docker build -t k8s-logging:0.0.1 .

kubectl apply -f deploy/deployment.yaml

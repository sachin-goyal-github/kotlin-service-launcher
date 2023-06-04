SERVICE=${PWD##*/}
VERSION=latest

./gradlew clean build
docker rmi $(docker images -qa 'sachingoyaldocker/sachin-goyal-github-'$SERVICE)
docker build --no-cache -t sachingoyaldocker/sachin-goyal-github-$SERVICE:$VERSION --build-arg SERVICE=$SERVICE .
docker push sachingoyaldocker/sachin-goyal-github-$SERVICE:$VERSION

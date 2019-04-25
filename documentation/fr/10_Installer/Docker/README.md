
# Docker : 1 conteneur pour tester Asqatasun

Essayer Asqatasun grâce à un unique conteneur Docker.

- C'est un gros conteneur qui n'est absolument pas conforme avec les [bonnes pratiques Docker](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/)
- Ne l'utiliser pas en production, car toutes les données seront perdues au redémarrage ou à la reconstruction du conteneur
- MAIS pour tester rapidement Asqatasun, il est parfait. :)

## 1. Créer un conteneur

### Créer un conteneur via [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)
```shell
     docker pull asqatasun/asqatasun  
     docker run --name asqa_test -d -p 8080:8080  asqatasun/asqatasun  
```


### Créer un conteneur en utilisant le fichier DockerFile
```shell
     git clone https://github.com/Asqatasun/Asqatasun.git  
     cd Asqatasun/docker/RELEASE
     docker build -t test_asqatasun . 
     docker run --name asqa_test -d -p 8080:8080 test_asqatasun
```

## 2. Utiliser Asqatasun sur votre ordinateur

- Dans votre navigateur web, saisir l'URL http://localhost:8080/asqatasun/ 
- Pour vous connecter, utiliser l'utilisateur suivant avec ce mot de passe :
  - `admin@asqatasun.org`
  - `myAsqaPassword`

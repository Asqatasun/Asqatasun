
# Use Asqatasun with a single container Docker

- This is a fat container, that is absolutely not compliant to [Docker best-practices](https://docs.docker.com/articles/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

## 1. Create a container

### Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)
```shell
     docker pull asqatasun/asqatasun  
     docker run --name asqa_test -d -p 8080:8080  asqatasun/asqatasun  
```


### Create a container from the DockerFile
```shell
     git clone https://github.com/Asqatasun/Asqatasun.git  
     cd Asqatasun/docker/single-container 
     docker build -t test_asqatasun . 
     docker run --name asqa_test -d -p 8080:8080 test_asqatasun
```

## 2. Use your local Asqatasun

- In your browser, go to http://localhost:8080/asqatasun/ 
- Use this user and this password :
  - `me@my-email.org`
  - `myAsqaPassword`

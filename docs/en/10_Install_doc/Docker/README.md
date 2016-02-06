
# Use Asqatasun with a single container Docker

- This is a fat container, that is absolutely not compliant to [Docker best-practices](https://docs.docker.com/articles/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

## 1. Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)

```sh
docker pull asqatasun/asqatasun  
docker run --name asqatasun -d -p 8080:8080  asqatasun/asqatasun  
```

## 2. Use your local Asqatasun

- In your browser, go to http://localhost:8080/asqatasun/ 
- Use this user and this password :
    - `me@my-email.org`
    - `myAsqaPassword`

## Developer, you wanna play harder? Come on!

Have a look at the Documentation in [Contributor Doc > Docker_build](../../30_Contributor_doc/Docker_build.md)

## Useful links

* [Asqatasun on Github](https://github.com/Asqatasun/Asqatasun)
* [Asqatasun Documentation](http://doc.asqatasun.org)
* [Asqatasun Forum](http://forum.asqatasun.org/)
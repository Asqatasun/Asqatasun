
# Use Asqatasun with Docker

## Asqatasun 4.1.x
- no docker image for this version of Asqatasun
- you can use Vagrant instead to run Asqatasun 4.1.x on your computer

see: https://gitlab.com/asqatasun/asqatasun-vagrant/-/tree/master/Ubuntu-18.04/Asqatasun_v4.1.0

```bash
git clone https://gitlab.com/asqatasun/asqatasun-vagrant.git
cd asqatasun-vagrant
vagrant up
vagrant ssh
    sudo -i  # Inside the box
    cd /vagrant
    ./asqatasun.sh
```


## Archive

### Asqatasun 4.0.3

see: https://gitlab.com/asqatasun/Asqatasun/-/tree/v4.0.3/docker

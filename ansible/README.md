#Asqatasun Ansible Role
[Ansible](http://docs.ansible.com/ansible/) is a configuration management tool that allows you to configure and manage other machines remotely. What makes it unique from other configuration management software like Chef or Puppet:

- It uses existing SSH infrastructure
- No needs for dedicated server, even your laptop can manage 100s of machine
- Easy to get started with it
- It uses "Facts", gathers information about system and environment before running the tasks
- Ansible Tasks are idempotent

* [Ansible Introduction](https://serversforhackers.com/getting-started-with-ansible/) - Very good and detail Ansible Introduction
This Ansible role will install the` Asqatasun Opensource Accessibility` on Ubuntu 12.04/14.04 LTS.

- If you want to read about the prerequisites of Asqatasun on Ubuntu, please follow this [link](https://github.com/Asqatasun/Asqatasun/blob/master/docs/prerequisites-webapp-doc.md)
- [Here](https://github.com/Asqatasun/Asqatasun/blob/master/docs/INSTALL.md) is the official installation procedure on Asqatasun github repo.

Also provided the `Vagrantfile` with the role, just run this command:
```shell
vagrant up
```
It will boot up the Ubuntu 14.04 LTS machine and install the Asqatasun on it:
```shell
PLAY [Installing the Asqatasun] ***********************************************

GATHERING FACTS ***************************************************************
ok: [asqatasun]

TASK: [asqatasun | Set Postfix options] ***************************************
changed: [asqatasun] => (item={'question': 'postfix/mailname', 'value': ' '})
changed: [asqatasun] => (item={'question': 'postfix/main_mailer_type', 'value': 'Satellite system'})

TASK: [asqatasun | Update locale Setting] *************************************
ok: [asqatasun]

TASK: [asqatasun | Reconfigure locale] ****************************************
skipping: [asqatasun]

TASK: [asqatasun | Update the Ubuntu repos] ***********************************
ok: [asqatasun]
```
Once the installation will be finished, just enter the following url to your browser to get the default Asqatasun page:
```
http://192.168.33.33:8080/asqatasun/
```
Here's the defaults values that role will use, please modify them as per your requirement, if needed:
``` yaml
asqatasun_parameters:
  db_name: 'asqatasundb'
  db_user: 'asqatasunuser'
  db_password: 'AsqatasunPassword'
  db_host: 'localhost'
  url: 'http://localhost:8080/asqatasun/'
  admin_email: 'admin@example.com'
  admin_passwd: 'asqatasun15'
```
Hope this role will help you. Thanks!


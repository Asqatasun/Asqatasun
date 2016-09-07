# Setting up an Apache front-end with HTTPS
 
## Overview

The big steps of this set up are:

1. Tomcat: configure an AJP connector
2. Tomcat: configure a dedicated virtual host
3. Apache: configure HTTPS with Let's Encrypt
4. Apache: configure the frontend
5. Restart services and check

## 0. Names and conventions

Let's define the following:

* `myasqatasun.mycompany.com`
  The FQDN (fully qualified domain name) of the machine hosting Asqatasun
* Asqatasun has been [installed on a server](../10_Install_doc/Asqatasun/README.md) (Ubuntu Linux 14.04) 
* ...and is available at http://myasqatasun.mycompany.com:8080/asqatasun/

## 1. TOMCAT configure an AJP connector
 
Make a backup copy of `/etc/tomcat7/server.xml`, then edit it.

Just below the comment `<!-- Define an AJP 1.3 Connector on port 8009 -->`, add:

```xml
<Connector port="8009"
    proxyName="myasqatasun.mycompany.com"
    proxyPort="443"
    URIEncoding="UTF-8"
    enableLookups="false"
    redirectPort="8443"
    protocol="AJP/1.3" />
```

## 2. TOMCAT configure a dedicated virtual host

Edit `/etc/tomcat7/server.xml`, just **above** the closing tag `</Engine>`, add:

```xml
<!-- asqatasun host -->
<Host name="myasqatasun.mycompany.com" 
    appBase="webapps"
    unpackWARs="true"
    autoDeploy="true">

    <Valve className="org.apache.catalina.valves.AccessLogValve"
        directory="logs"
        prefix="vhost-asqatasun_access_log."
        suffix=".log"
        pattern="common"
        resolveHosts="false"/>
        
    <Context path=""
        docBase="asqatasun"
        reloadable="true"/>
</Host>
```

## 3. APACHE configure HTTPS with Let's Encrypt

As recommended by Let's Encrypt, use [CertBot](https://certbot.eff.org/) to create your certificate and eventually configure your webserver.
 
## 4. APACHE configure the frontend

In your Apache virtual host configuration file (typically `/etc/apache2/sites-enabled/000-default-le-ssl.conf`), add:

```
SSLProxyEngine on
<Proxy *>
        Order deny,allow
        Allow from all
</Proxy>
ProxyPassMatch                  ^/External-Images/http://(.*)$  http://$1
ProxyPassMatch                  ^/External-Images/https://(.*)$ https://$1
ProxyPass                       /                               ajp://localhost:8009/
ProxyPassReverse                /                               https://myasqatasun.mycompany.com/
ProxyPassReverseCookiePath      /                               /
```

Enable the following modules:

```sh
sudo apt-get install libapache2-mod-proxy-html libxml2-dev
sudo a2enmod proxy proxy_html proxy_http proxy_ajp xml2enc
```

## 5. Restart and check

```sh
sudo ser
```

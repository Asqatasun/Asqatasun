# Add an Apache frontend with HTTPS

You should have already done these steps:

1. [Check Hardware provisioning](Hardware_network_provisioning.md)
2. [Download Asqatasun](Download.md)
3. [Check pre-requisites](Pre-requisites.md)
4. [Install](Installation.md)

(Ever need help ? Go to [Asqatasun Forum](http://forum.asqatasun.org).)

## Configure Apache Virtual Host

Let says the installed Asqatasun will be reachable through `asqatasun.company.com`. 

Create the file `/etc/apache2/sites-available/asqatasun.company.com.conf` and add the following content:

```
<VirtualHost *:80>
	ServerName asqatasun.company.com
	ServerAdmin webmaster@company.com
	DocumentRoot /var/www-vhosts/asqatasun.company.com

	ErrorLog ${APACHE_LOG_DIR}/asqatasun.company.com_error.log
	CustomLog ${APACHE_LOG_DIR}/asqatasun.company.com_access.log combined
    LogLevel warn
	ServerSignature Off

	<Location />
		Require all granted
	</Location>
</VirtualHost>
```

Activate virtual host and reload Apache

```
sudo a2ensite asqatasun.company.com
sudo service apache2 restart
```

## Add HTTPS with Let's Encrypt

Follow all explanations from https://certbot.eff.org/

**Note**: don't forget to tell Cerbot to redirect HTTP to HTTPS (so that only HTTPS is used).

## Configure Apache (to talk to Tomcat)

Install the following apache's modules

```shell
sudo a2enmod proxy proxy_ajp proxy_html proxy_http xml2enc
```

Certbot created a file `/etc/apache2/sites-available/asqatasun.company.com-le-ssl.conf`.
Edit this file, and just **before** the closing tag `</VirtualHost>`, add the following content:
 
```
SSLProxyEngine on
<Proxy *>
    Order deny,allow
    Allow from all
</Proxy>

ProxyPassMatch                  ^/External-Images/http://(.*)$  http://$1
ProxyPassMatch                  ^/External-Images/https://(.*)$ https://$1
ProxyPass                       /                               ajp://localhost:8009/
ProxyPassReverse                /                               https://asqatasun.company.com/
ProxyPassReverseCookiePath      /                               /
```

## Configure Tomcat

Make a backup copy of `/etc/tomcat7/server.xml` and modify it this way.

### AJP Connector

**Before** the tag `<Engine ...`, add the following:

```
<Connector port="8009"
    proxyName="asqatasun.company.com"
    proxyPort="443"
    URIEncoding="UTF-8"
    enableLookups="false"
    redirectPort="8443"
    protocol="AJP/1.3" />
```

After the tag `</Host>` and before the tag `</Engine>`, add the following:

```
<!-- asqatasun host -->
<Host name="asqatasun.company.com" 
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

## Test it all

```shell
sudo service tomcat7 restart
sudo service apache2 restart
```

then browse https://asqatasun.company.com/
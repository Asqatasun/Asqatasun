# Debugging Asqatasun

## Increase log level

To increase log level, modify the file

```
<TOMCAT_ASQATASUN_WEBAPP>/WEB-INF/classes/log4j.properties
```

...and selectively replace `INFO` by `DEBUG`.

You can also add specific log on a given package. For instance you are troubleshooting
the package `org.asqatasun.util.http`, you can add a dedicated log level to this very
package by adding:

```
log4j.logger.org.asqatasun.util.http=DEBUG
```

## Observe Firefox WebDriver Display

Idea: to actually see the headless Firefox (no more headless thus).

1. `service xvfb stop`
1. `xhost +`
1. Modify `/etc/default/tomcat7` to remplace `-Ddisplay=:99` by `-Ddisplay=:0.0`
1. `service tomcat7 restart`

When firing up an audit, you will see a Firefox window appearing and the page being loaded
(page-audit) or the scenario executed (scenario-audit).
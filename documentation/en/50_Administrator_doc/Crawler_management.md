# Crawler management

## Increase number of pages per site audit (aka maxDocuments or max-documents)

By default a site audit is restricted to 1'000 pages to avoid useless disk space consumption.
To increase this value, you should do the following steps.

### 1. Edit file tgol-beans-audit-set-up-form.xml

From the webapp directory (typically `/var/lib/tomcat7/webapps/asqatasun`),
edit file `WEB-INF/conf/mvc/form/tgol-beans-audit-set-up-form.xml`.

In section `<bean id="maxDocumentsFormFieldBuilder"`, set the desired value:
  
```xml
<property name="maxValue" value="1000"/>
```

### 2. Edit file tgol-beans-contract-management.xml

Same way, edit file `WEB-INF/conf/mvc/form/tgol-beans-contract-management.xml`.
 
In section `<bean id="maxDocumentsOptionFormFieldBuilder"`, set the
**same** value as you used above:

```xml
<property name="maxValue" value="1000"/>
```

### 3. Restart Tomcat

```sh
service tomcat7 restart
```
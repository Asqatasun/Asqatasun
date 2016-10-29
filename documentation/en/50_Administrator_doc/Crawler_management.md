# Crawler management

## Disable compliance to robots.txt rules

By default, Asqatasun obeys to the [rules from the robots.txt](http://www.robotstxt.org/). 

You may need to bypass these rules. This is not a good practice and is **not recommanded**. 
But if you absolutely need it, you will have to do the following.

1. From your webapp directory (typically `/var/lib/tomcat7/webapps/asqatasun` on Ubuntu 14.04), 
   modify the file `WEB-INF/conf/crawler/asqatasun-crawler-beans-site.xml`
2. Locate the entry `<bean id="metadata" class="org.archive.modules.CrawlMetadata" autowire="byName">`
3. Add underneath the property `<property name="robotsPolicyName" value="ignore"/>`
4. Restart Tomcat

The whole stanza would look like this:

```xml
    <!-- CRAWL METADATA: including identification of crawler/operator -->
    <bean id="metadata" class="org.archive.modules.CrawlMetadata" autowire="byName">
        <property name="operatorContactUrl" value="[see override above]"/>
        <property name="jobName" value="[see override above]"/>
        <property name="description" value="[see override above]"/>
        <property name="userAgentTemplate" value="asqatasun +@OPERATOR_CONTACT_URL@"/>
        <property name="robotsPolicyName" value="ignore"/>
    </bean>
```

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
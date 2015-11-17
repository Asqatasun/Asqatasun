#!/bin/bash

TOMCAT_HOST_PATH=/var/lib/tomcat7/webapps/tanaguru

# Copy jar in classpath
cp lib/*.jar $TOMCAT_HOST_PATH/WEB-INF/lib

# Add spring conf for mvc part
sed -i -e "s#</beans>#\t<import resource=\"classpath:conf/context/rgaa22/web-app/rgaa22-beans-webapp.xml\"/>\n\n</beans>#" \
	    "$TOMCAT_HOST_PATH/WEB-INF/tgol-web-app-servlet.xml"

# Add i18n files 
sed -i -e "s#<!--rules-commons i18n#<!--rgaa2.2 i18n files -->\n\t\t<value>classpath:i18n/referential-rgaa22-I18N</value>\n\t\t<value>classpath:i18n/theme-rgaa22-I18N</value>\n\t\t<value>classpath:i18n/criterion-rgaa22-I18N</value>\n\t\t<value>classpath:i18n/rule-rgaa22-I18N</value>\n\n\t\t<!--rules-commons i18n#" \
	    "$TOMCAT_HOST_PATH/WEB-INF/conf/mvc/tgol-beans-i18n.xml"
#!/bin/bash

TOMCAT_HOST_PATH=/var/lib/tomcat6/webapps/tanaguru
#TOMCAT_HOST_PATH=/var/lib/tomcat6/vhost-int-tanaguru/ROOT/

# Copy jar in classpath
cp lib/*.jar $TOMCAT_HOST_PATH/WEB-INF/lib

# Add spring conf for mvc part
sed -i -e "s#</beans>#\t<import resource=\"classpath:conf/context/seo/web-app/seo-beans-webapp.xml\"/>\n\n</beans>#" \
	    "$TOMCAT_HOST_PATH/WEB-INF/tgol-web-app-servlet.xml"

# Add spring conf for seo part
sed -i -e "s#</beans>#\t<import resource=\"classpath:conf/context/seo/engine/seo-beans-engine.xml\"/>\n\n</beans>#" \
	    "$TOMCAT_HOST_PATH/WEB-INF/conf/tgol-service.xml"

# Add i18n files 
sed -i -e "s#<!--rules-commons i18n#<!--seo1.0 i18n files -->\n\t\t<value>classpath:i18n/referential-seo-I18N</value>\n\t\t<value>classpath:i18n/theme-seo-I18N</value>\n\t\t<value>classpath:i18n/criterion-seo-I18N</value>\n\t\t<value>classpath:i18n/rule-seo-I18N</value>\n\n\t\t<!--rules-commons i18n#" \
	    "$TOMCAT_HOST_PATH/WEB-INF/conf/mvc/tgol-beans-i18n.xml"

# Add specific test representations
echo -e "\n# Seo 1.0 test representation overwrite\nSeo-01021-representation-index=3\nSeo-01041-representation-index=4\nSeo-06041-representation-index=3\nSeo-07061-representation-index=3\n:q!
" >> "$TOMCAT_HOST_PATH/WEB-INF/classes/representation.properties"
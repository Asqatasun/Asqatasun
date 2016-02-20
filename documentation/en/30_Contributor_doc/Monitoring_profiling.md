# Monitoring and profiling

This page describes how to profile Asqatasun web application with Netbeans Profiler or monitor it with Java VisualVM.

## Netbeans Profiler

To profile Asqatasun web application with the embedded netbeans profiler, install the profile plugin if not installed, go to the profile section in the menu and select "Attach Profiler".

A popup named "Attach Profiler" is displayed to configure the profiler.

Click on the "define..." link to set up the attachment ; a "Attach Wizard" popup is displayed.

On the first step (Select Targer Type) :

* Select "J2EE Web/App Server" as Targer Type
* Select "Tomcat x.x" as J2EEWeb/App Server Type.
* Select "Remote" as attach mode (even if the tomcat process is run locally)
* Select "Direct" as Attach invocation

On the second step (Remote System)

* Type the name of the host on which tomcat is launched (can be 127.0.0.1 if run locally)
* Select "Linux 32 bit" JVM as Host OS & JVM

On the third step (Review Attach Settings)

* Check the settings are correct
* Go to next step (don't click on Finish)

On the fourth step (Manual integration)

* Follow the instructions to customize the tomcat start script. Depending the system distribution, the script to modify is not "$REMOTE_CATALINA_HOME/bin/catalina.sh" as described but /etc/init.d/tomcat55
* Click on Finish button

Depending what you're tracking, choose the appropriate options on "Analyze Memory" and "Analyse CPU" sections (The"Record stack trace for allocations" option may consume many system resources but is very usefull to track memory leaks)

Launch Tomcat with the customized start script (the process is paused until a profiler try to connect)

Validate the "Attach Profiler" popup by clickink the "Attach" button.

## Java VisualVM

For more informations about what Java VisualVM can do, please refer to http://java.sun.com/javase/6/docs/technotes/guides/visualvm/index.html

### TOMCAT CONFIGURATION

Create a copy of the tomcat start script ($REMOTE_CATALINA_HOME/bin/catalina.sh or 
/etc/init.d/tomcat55 depending on the system distribution) and rename it to tomcat_visualvm.sh

Add the following lines in the copied start script :

```
JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8086 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"  
```

Start the modified tomcat start script :

```
sudo invoke-rc.d tomcat_visualvm.sh start
```

### VISUALVM START-UP

Set the JAVA_HOME env property (depends on the distribution and the java version)

Launch VisualVM

```
$JAVA_HOME/bin/jvisualvm 
```

Java Visual VM is displayed, the right menu lists all the java applications running on the machine.

Right click on the "Local" icon in the right menu list and select "Add JMX Connection"

Fill in the Connection field on the Add JMX Connection popup with the following value :

```
localhost:8086
```

Enjoy!
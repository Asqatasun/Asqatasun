# Asqatasun FAQ (Frequently Asked Questions)

## Asqatasun not responding / Tomcat error: `SEVERE: Error listenerStart`

### If you ever have

...Asqatasun not responding, and your Tomcat logfile (typically `/var/log/tomcat7/catalina.out`)
containing the following error:

```log
Jan 11, 2016 10:08:43 AM org.apache.catalina.core.StandardContext startInternal
SEVERE: Error listenerStart
Jan 11, 2016 10:11:42 AM org.apache.catalina.util.SessionIdGenerator createSecureRandom
INFO: Creation of SecureRandom instance for session ID generation using [SHA1PRNG] took [179,168] milliseconds.
Jan 11, 2016 10:11:42 AM org.apache.catalina.core.StandardContext startInternal
SEVERE: Context [/tanaguru] startup failed due to previous errors
Jan 11, 2016 10:11:42 AM org.apache.catalina.startup.HostConfig deployDirectory
INFO: Deploying web application directory /var/lib/tomcat7/webapps/ROOT
Jan 11, 2016 10:11:42 AM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler ["http-bio-8080"]
Jan 11, 2016 10:11:42 AM org.apache.catalina.startup.Catalina start
INFO: Server startup in 391887 ms
```

and/or in your Asqatasun logfile (typically `/var/log/asqatasun/asqatsun.log`) containing the following error:

```log
11-01-2016 09:46:24:323 62473 WARN  com.mchange.v2.resourcepool.BasicResourcePool  - com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@4bcdc28b -- Acquisition Attempt Failed!!! Clearing pending acquires. While trying to acquire a needed new resource, we failed to succeed more than the maximum number of allowed acquisition attempts (30). Last acquisition attempt exception:
com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure

The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
        at sun.reflect.GeneratedConstructorAccessor19.newInstance(Unknown Source)
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.lang.reflect.Constructor.newInstance(Constructor.java:526)
        at com.mysql.jdbc.Util.handleNewInstance(Util.java:411)
        at com.mysql.jdbc.SQLError.createCommunicationsException(SQLError.java:1129)
        at com.mysql.jdbc.MysqlIO.<init>(MysqlIO.java:358)
        at com.mysql.jdbc.ConnectionImpl.coreConnect(ConnectionImpl.java:2489)
        at com.mysql.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:2526)
        at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2311)
        at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:834)
        at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:47)
        at sun.reflect.GeneratedConstructorAccessor16.newInstance(Unknown Source)
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.lang.reflect.Constructor.newInstance(Constructor.java:526)
        at com.mysql.jdbc.Util.handleNewInstance(Util.java:411)
        at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:416)
        at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:347)
        at com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:146)
        at com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:195)
        at com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:184)
        at com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:200)
        at com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1086)
        at com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1073)
        at com.mchange.v2.resourcepool.BasicResourcePool.access$800(BasicResourcePool.java:44)
        at com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1810)
        at com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:648)
Caused by: java.net.ConnectException: Connection refused
        at java.net.PlainSocketImpl.socketConnect(Native Method)
        at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)
        at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)
        at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)
        at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
        at java.net.Socket.connect(Socket.java:579)
        at java.net.Socket.connect(Socket.java:528)
        at java.net.Socket.<init>(Socket.java:425)
        at java.net.Socket.<init>(Socket.java:241)
        at com.mysql.jdbc.StandardSocketFactory.connect(StandardSocketFactory.java:256)
        at com.mysql.jdbc.MysqlIO.<init>(MysqlIO.java:308)
        ... 20 more
```

### This means

...that the web-app can't connect to Mysql.

### So you should

* verify your Mysql credentials in `/etc/asqatasun/asqatasun.conf` (login, password, host, port, database name). 
* verify you don't have a firewall blocking connections. Typically, you should allow connections from 127.0.0.1 to 127.0.0.1:3306

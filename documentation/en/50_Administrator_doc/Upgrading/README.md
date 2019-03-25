# Upgrading Asqatasun

## v4.0.3 to v4.1.0

There is a change of JDK (from 7 to 8) between these two releases. We run on limited volunteer energy, and providing support for both Java7 and Java8 is beyond our abilities by now (2019-01). So we **do no provide upgrade from v4.0.3 to v4.1.0**.

Nevertheless, there exists a beginning of [unofficial + untested Upgrade from Asqatasun v4.0.3 to v4.1.0](Upgrade_from_v4.0.3_to_v4.1.0.md). 

Other "upgrade paths" could be:

* upgrade the Java stack on the host, keep the DB, install v4.1.0 and use existing DB
* export the data from DB, install v4.1.0 on a new host, then import data (we are thinking of a script to easy export/import) 

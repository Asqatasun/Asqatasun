# Native Mysql usage

For performance reasons, some native mysql queries are used in Asqatasun.

These queries are present in 3 classes (found by searching the "createNativeQuery" occurence in the source code) :

* ContentDAOImpl (persistence subproject of engine) -> 6 queries
* StatisticsDAOImpl (tgol-persistence subproject of web-app) -> 10 queries
* TgolParameterDAOImpl (tgol-persistence subproject of web-app) -> 1 query

In case of change of the JDBC layer, these queries HAVE to be refactored to fit with the new data source.

Another approach may lead to a change of these queries to make them use the JPA interface
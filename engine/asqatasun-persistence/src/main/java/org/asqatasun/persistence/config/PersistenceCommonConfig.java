package org.asqatasun.persistence.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Properties;

/**
 * Created by jkowalczyk on 5/18/16.
 */
public abstract class PersistenceCommonConfig {

    public static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    public static final String HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
    public static final String HIBERNATE_DEFAULT_BATCH_FETCH_SIZE = "hibernate.default_batch_fetch_size";
    public static final String HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
    public static final String HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    public static final String HIBERNATE_USE_OUTER_JOIN = "hibernate.use_outer_join";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";

    public static String MYSQL_DRIVER_CLASSNAME = "com.mysql.cj.jdbc.Driver";
    public static String POSTGRES_DRIVER_CLASSNAME = "org.postgresql.Driver";
    public static String HSQL_DRIVER_CLASSNAME = "org.hsqldb.jdbcDriver";

    public static String MYSQL_KEY = "mysql";
    public static String POSTGRES_KEY = "postgresql";
    public static String HSQL_KEY = "hsqldb";

    public static String MYSQL_MIGRATION_PREFIX = "MSDB";
    public static String POSTGRES_MIGRATION_PREFIX = "PSDB";
    public static String HSQL_MIGRATION_PREFIX = "HSQLDB";

    public static String MYSQL_HIBERNATE_DIALECT = "org.asqatasun.dialect.AsqatasunMySQL5InnoDBDialect";
    public static String POSTGRES_HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQL82Dialect";
    public static String HSQL_HIBERNATE_DIALECT = "org.hibernate.dialect.HSQLDialect";

    @Value("${jpa.showSql:false}")
    private Boolean hibernateShowSql;
    @Value("${hibernate.max_fetch_depth:3}")
    private int hibernateMaxFetchDepth;
    @Value("${hibernate.jdbc.batch_size:1000}")
    private int hibernateJdbcBatchSize;
    @Value("${hibernate.default_batch_fetch_size:500}")
    private int hibernateDefaultBatchFetchSize;
    @Value("${hibernate.use_outer_join:false}")
    private Boolean hibernateUseOuterJoin;
    @Value("${hibernate.cache.use_second_level_cache:true}")
    private Boolean hibernateUse2ndLevelQueryCache;
    @Value("${hibernate.cache.use_query_cache:true}")
    private Boolean hibernateUseQueryCache;
    @Value("${hibernate.cache.region.factory_class:org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory}")
    private String hibernateRegionFactory;
    @Value("${app.engine.persistence.connection.idleConnectionTestPeriod:3600}")
    private int idleConnectionTestPeriod;
    @Value("${app.engine.persistence.connection.initialPoolSize:5}")
    private int initialPoolSize;
    @Value("${app.engine.persistence.connection.maxPoolSize:1000}")
    private int maxPoolSize;
    @Value("${app.engine.persistence.connection.minPoolSize:5}")
    private int minPoolSize;
    @Value("${app.engine.persistence.connection.acquirePoolIncrement:5}")
    private int acquireIncrement;
    @Value("${app.engine.persistence.locationAutomatedCommon:sql/automated/common}")
    private String locationAutomatedCommon;
    @Value("${app.engine.persistence.locationAutomatedTestOnly:sql/automated/testOnly}")
    private String locationAutomatedTestOnly;

    /**
     * @param url
     * @param username
     * @param password
     * @return
     * @throws PropertyVetoException
     */
    public DataSource setUpComboPooledDataSource (
            String url,
            String username,
            String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(PersistenceCommonConfig.getDriverClassNameFromUrl(url));
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setDebugUnreturnedConnectionStackTraces(true);
        return dataSource;
    }

    /**
     * @param url
     * @param username
     * @param password
     * @return
     */
    public DataSource setUpBasicDataSource(
            String url,
            String username,
            String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(PersistenceCommonConfig.getDriverClassNameFromUrl(url));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }

    /**
     *
     * @param dataSource
     * @param url
     * @return
     */
    public Flyway setUpFlyway(DataSource dataSource, String url) {
        FluentConfiguration config = Flyway.configure()
            .dataSource(dataSource)
            .sqlMigrationPrefix(getFlywayMigrationPrefixFromUrl(url))
            .locations(locationAutomatedCommon);
        if (PersistenceCommonConfig.isHsqlDb(url)) {
            config.baselineOnMigrate(true).baselineVersion("10.0.0");
        }
        return config.load();
    }

    /**
     *
     * @param dataSource
     * @param url
     * @param packagesToScan
     * @return
     */
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            String url,
            String... packagesToScan) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(packagesToScan);
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        hibernateAdapter.setShowSql(hibernateShowSql);
        final Properties jpaProperties = new Properties();
        jpaProperties.put(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, hibernateUse2ndLevelQueryCache);
        jpaProperties.put(HIBERNATE_CACHE_USE_QUERY_CACHE, hibernateUseQueryCache);
        jpaProperties.put(HIBERNATE_CACHE_REGION_FACTORY_CLASS, hibernateRegionFactory);
        jpaProperties.put(HIBERNATE_GENERATE_STATISTICS, false);
        jpaProperties.put(HIBERNATE_JDBC_BATCH_SIZE, hibernateJdbcBatchSize);
        jpaProperties.put(HIBERNATE_MAX_FETCH_DEPTH, hibernateMaxFetchDepth);
        jpaProperties.put(HIBERNATE_USE_OUTER_JOIN, hibernateUseOuterJoin);
        jpaProperties.put(HIBERNATE_DEFAULT_BATCH_FETCH_SIZE,hibernateDefaultBatchFetchSize);
        jpaProperties.put(HIBERNATE_DIALECT, getHibernateDialedFromUrl(url));
        if (PersistenceCommonConfig.isHsqlDb(url)) {
            jpaProperties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        }
        emf.setJpaProperties(jpaProperties);
        emf.setJpaVendorAdapter(hibernateAdapter);

        return emf;
    }

    /**
     *
     * @param url
     * @return
     */
    private static String getDriverClassNameFromUrl(String url) {
        return getString(url, MYSQL_DRIVER_CLASSNAME, POSTGRES_DRIVER_CLASSNAME, HSQL_DRIVER_CLASSNAME);
    }

    private static String getString(String url, String mysqlDriverClassname, String postgresDriverClassname, String hsqlDriverClassname) {
        if (StringUtils.isBlank(url)) {
            return mysqlDriverClassname;
        }
        if (StringUtils.contains(url,MYSQL_KEY)) {
            return mysqlDriverClassname;
        } else if (StringUtils.contains(url,POSTGRES_KEY)) {
            return postgresDriverClassname;
        } else if (StringUtils.contains(url,HSQL_KEY)) {
            return hsqlDriverClassname;
        } else {
            return mysqlDriverClassname;
        }
    }

    private static boolean isHsqlDb(String url) {
        return StringUtils.contains(url,HSQL_KEY);
    }

    /**
     *
     * @param url
     * @return
     */
    private static String getHibernateDialedFromUrl(String url) {
        return getString(url, MYSQL_HIBERNATE_DIALECT, POSTGRES_HIBERNATE_DIALECT, HSQL_HIBERNATE_DIALECT);
    }

    /**
     *
     *
     * @param url
     * @return
     */
    private static String getFlywayMigrationPrefixFromUrl(String url) {
        return getString(url, MYSQL_MIGRATION_PREFIX, POSTGRES_MIGRATION_PREFIX, HSQL_MIGRATION_PREFIX);
    }
}

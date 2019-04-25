# Functional tests in Asqatasun

Functional tests are done with Selenium WebDriver. The actual tests are in the `testing-tools/tgol-test-scenario`.

## Running all functional tests

Provided you have:

* a running Linux environnement with graphical environment (i.e. an XServer running)
* your local Asqatasun Docker image running at `http://localhost:8085/asqatasun/`
* your Firefox ESR 31.4 installed in `/opt/firefox/`, 

...you may run all the functional tests with:

```sh
cd testing-tools/tgol-test-scenario
mvn test \
    -Dadmin.user=admin@asqatasun.org \
    -Dadmin.password=myAsqaPassword \
    -Dhost.location=http://localhost:8085/asqatasun/ \
    -Dfirefox.path=/opt/firefox/firefox
```

## Running one given functional test

Say you just want to debug the ["login/logout" test](https://github.com/Asqatasun/Asqatasun/blob/develop/testing-tools/tgol-test-scenario/src/test/java/org/asqatasun/tgol/test/scenario/LoginLogoutScenarioTest.java),
you have to add the `-Dtest=...` and add it **before** the `test` command:

```sh
cd testing-tools/tgol-test-scenario
mvn \
    -Dtest=LoginLogoutScenarioTest \
    test \
    -Dadmin.user=admin@asqatasun.org \
    -Dadmin.password=myAsqaPassword \
    -Dhost.location=http://localhost:8085/asqatasun/ \
    -Dfirefox.path=/opt/firefox/firefox
```


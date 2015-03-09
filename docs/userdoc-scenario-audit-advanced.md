# Tanaguru scenario advanced usage

## Triggering additional audits in a scenario

Inside a given scenario, an audit is triggered each time the URL changes (e.g. when
the user clicks a links or submits a form button).

You may trigger additional audits by adding the Selenium command `storeCurrentUrl`.



## When a page is audit

@@@ storeCurrentUrl + "=myText"

@@@ pause 1000ms

@@@ extension Firefox 2 selector


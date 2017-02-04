# SQL procedures

Here is the list of SQL procedures available since Asqatasun 4.0.4+

**Note**: each SQL procedure has a shell script wrapper, located in `/usr/loca/bin/` (hint: names of wrappers begin with "ASQA_"

## Last_audit

List last audits.

```mysql
call last_audits(n);
```

With:

* `n`: INT, number of audits to list.

## List_running_acts

List all running acts. May be useful if an audit crashes, you can find its `Id_Act`s and `Id_Audit` and delete them manually.

```mysql
call list_running_acts();
```

## Delete_audit_from_id

Delete an audit based on its id.

```mysql
call list_running_acts(id);
```

With:

* `id`: INT, the id of the audit to delete

## Contract_create

Create a contract for a given user.

```mysql
call contract_create(idUser, label, url, referential, audit_page, audit_site, audit_file, audit_scenario, audit_manual, maxDoc);
```

With:

* `idUser`: INT, the id of the user to whom the contract is added
* `label`: VARCHAR(255), the name you give to the contract
* `url`: VARCHAR(1024), if specified all audits will be restricted to that domain (`http://mysite.org/`) or URL (i.e. `http://mysite.org/directory/`). This does not apply to file-audit or scenario-audit.
* `referential`: VARCHAR(10), code name of the referential. As of february 2017, to be choosen amongst `RGAA3`, `RGAA32016`, `SEO`
* `audit_page`: BOOLEAN, whether to authorize page audit
* `audit_site`: BOOLEAN, whether to authorize site-wide audit (i.e. authorize crawler usage)
* `audit_file`: BOOLEAN, whether to authorize audit of uploaded HTML files
* `audit_scenario`: BOOLEAN, whether to authorize audit of scenarios
* `audit_manual`: BOOLEAN, whether to authorize a manual audit to be ran (after the automated audit is complete). Works only for page-audit.
* `maxDoc`: for site audit, maximum number of pages the crawler has to browse (say 1'000). Warning: 10'000 is a huge value. Important note: for contract without site-audit permitted, `maxDoc` **must** have a `NULL` value.

### Mandatory fields

* `idUser`
* `label`
* `url` is needed only for contract authorizing site-audit (otherwise you allow your user to audit the whole internet..)
* at least one type of audit amongst `audit_page`, `audit_site`, `audit_file`, `audit_scenario`

### Example of contract: Site-wide audit on Wikipedia for accessibility (RGAA)

We want to create a contract:

* to user whose id is 1,
* on http://en.wikipedia.org/
* for accessibility (referential RGAA3),
* allowing only site-audit (none of the other kinds of audit),
* in this case manual-audit is not relevant,
* and we limit the crawler action to 1000 pages

```mysql
call contract_create(1, "Wikipedia RGAA", "http://en.wikipedia.org/", "RGAA3", 0, 1, 0, 0, 0, "1000");
```

### Example of contract: Site-wide audit on Wikipedia for SEO

We want to create the same contract but only for SEO. Easy, just change the referential (don't forget, this is the technical codename of the referential that must be passed as argument)

```mysql
call contract_create(1, "Wikipedia RGAA", "http://en.wikipedia.org/", "SEO", 0, 1, 0, 0, 0, "1000");
```

### Example of contract: Site-wide audit + page-audit + manual-audit on Wikipedia for accessibility

Like the previous one, but we add the page-audit feature, and enable the manual-audit option.

```mysql
call contract_create(1, "Wikipedia RGAA", "http://en.wikipedia.org/", "SEO", 1, 1, 0, 0, 1, "1000");
```

Note: the manual-audit option concerns only page-audit.

### Example of contract: page-audit on any webpage over the internet, for accessibility

This time, we do not provide any `url` parameter. We use to call this kind of contract "openbar".

```mysql
call contract_create(1, "Openbar RGAA", "", "RGAA3", 1, 0, 0, 0, 0, "NULL");
```

Note the `NULL` value for `maxDoc` parameter.

### Example of contract: page-audit + file-audit + scenario-audit on any webpage over the internet, for accessibility, plus manual-audit

Taking the previous example, we just add features file-audit and scenario-audit, and enable the manual-audit option.

```mysql
call contract_create(1, "Openbar RGAA", "", "RGAA3", 1, 0, 1, 1, 1, "NULL");
```
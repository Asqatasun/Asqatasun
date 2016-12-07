# SQL procedures

Here is the list of SQL procedures available since Asqatasun 4.0.4+

## Last_audit

```mysql
call last_audits(10);
```

List 10 last audits.

## List running acts

```mysql
call list_running_acts();
```

List all running acts. May be useful if an audit crashes, you can find its `Id_Act`s and `Id_Audit` and delete them manually.

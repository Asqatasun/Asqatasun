# Queries to retrieve URLs

## Retrieve URLs giving result FAILED on test 3.4.1 from referential RGAAv3 

```sql
SELECT wr.Url, pr.Definite_Value
FROM PROCESS_RESULT pr
INNER JOIN TEST t USING (Id_Test)
INNER JOIN WEB_RESOURCE wr USING (Id_Web_Resource)
WHERE t.Cd_Test="Rgaa30-3-4-1"
AND pr.Definite_Value="FAILED"
; 
```

Let's add the date of the audit so we can order URLs by date (to retest them)

```sql
SELECT a.Dt_Creation, wr.Url, pr.Definite_Value
FROM PROCESS_RESULT pr
INNER JOIN TEST t USING (Id_Test)
INNER JOIN WEB_RESOURCE wr USING (Id_Web_Resource)
INNER JOIN AUDIT a USING (Id_Audit)
WHERE t.Cd_Test="Rgaa30-3-4-1"
    AND pr.Definite_Value="FAILED"
ORDER BY a.Dt_Creation ASC 
; 
```
 



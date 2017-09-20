# i18n

Asqatasun i18n files use UTF-8 character encoding.

## Warning: do not translate internal IDs
```bash
##### For [form.resultNumber]
##### ---> Do not translate "choice", it is an internal ID
form.resultNumber={0} {0,choice,1#result|1<results}
form.resultNumber={0} {0,choice,1#résultat|1<résultats}
form.resultNumber={0} {0,choice,1#resultado|1<resultados}
form.resultNumber={0} {0,choice,1#개의 결과|1<개의 결과}
```

## Character encoding

Java i18n files however must be ISO-8859-1.
But Asqatasun i18n files use UTF-8 character encoding.

### Single quote 

In i18n files, the single quote [ `'` - apostrophe ] may be problematic.
If you can prefer to using [typographic apostrophe](https://en.wikipedia.org/wiki/Apostrophe#Entering_apostrophes) [ `’` ].
 
Some cases:

1. `depth=Profondeur maximale d'un <strong>audit</strong>`
    it works
2. `depth=Profondeur maximale d''un <strong>audit</strong>`
    appears like this: `Profondeur maximale d''un <strong>audit</strong>`
    
    In this case, it is necessary to use one of the following elements:
    `depth-rule=depth=Profondeur maximale d’un <strong>audit</strong>`
    `depth-rule=depth=Profondeur maximale d'un <strong>audit</strong>`
    `depth-rule=depth=Profondeur maximale d&apos;un <strong>audit</strong>`
    `depth-rule=depth=Profondeur maximale d&#39;un <strong>audit</strong>`

3. `depth-rule=Prodondeur d'une page de 0 à {0} d'une <strong>page</strong>`
    appears like this: `Prodondeur dune page de 0 à {0} dune <strong>page</strong>`
    
    In this case, it is necessary to use one of the following elements:
    `depth-rule=Prodondeur d’une page de 0 à {0} d’une <strong>page</strong>`
    `depth-rule=Prodondeur d''une page de 0 à {0} d''une <strong>page</strong>`
    `depth-rule=Prodondeur d&apos;une page de 0 à {0} d&apos;une <strong>page</strong>`
    `depth-rule=Prodondeur d&#39;une page de 0 à {0} d&#39;une <strong>page</strong>`



## Convert a single file

### UTF8 to ISO-8859-1 with Unicode escapes
Converts a file encoded in UTF8 to a file in in ISO-8859-1 with Unicode escapes.
```bash
native2ascii -encoding utf8 example.utf8.txt example.propertie
```

### ISO-8859-1 with Unicode escapes to UTF8
Converts a file encoded in ISO-8859-1 with Unicode escapes to a file in UTF8 character encoding.
```bash
native2ascii -encoding utf8 -reverse example.propertie   example.reverse.utf8.txt
```


## native2ascii-maven-plugin
native2ascii-maven-plugin is only necessary 
for the export of an audit (PDF, CSV and CSV)

- https://davidsimpson.me/2015/03/04/automated-native2ascii-character-conversion-internationalization-atlassian-add-ons/
- https://blog.crafting-labs.fr/2010/09/25/fichiers-properties-java-en-utf-8-avec-maven/
- https://stackoverflow.com/questions/7105039/maven-how-to-configure-native2ascii-maven-plugin?rq=1
  - http://grepcode.com/project/repo1.maven.org/maven2/org.codehaus.mojo/native2ascii-maven-plugin/

# Referential-Creator helps the referential creation

## Pre-requisites

Build asqatasun (it may take a while !!)

```sh
cd Asqatasun/

# build
mvn clean install
```

Build the referential-creator maven plugin

```sh
cd Asqatasun/rules/referential-creator-maven-plugin/
mvn clean install
```

## Prepare and generate your referential

## Prepare your referential (CSV File)

### Mandatory columns

* The theme column : Each cell contain a thematic id, defined by an integer (should be incremented).
* The theme_xx column : Each cell contain the theme label to be display on the UI.
* The test column : Defined by two numbers separated by a dash. The first is the theme number (the number in the first cell on the current line and the second is the test number (for each theme, a test code can't be used many times).
* The test-label_xx column : Each cell contains the test label to be display on the UI.

The minimum header line to create context:

```cvs
theme;theme_en;test;test-label_en
``` 

Note that you can replace `en` by any other language.

### Optional columns

* The level column: default value is 1. You can override this value :
    * 1: high
    * 2: medium
    * 3: low
* The scope column: default value is 1.
    * For a page rule, set the value to 1.
    * For a site rule, set the value to 2.
    * For a site and page rule, set the value to 3.
* The class-name column: default value use the test code number.
  If you want to name your class, set the cell with the class name. i.e. `MyClassRuleToTestCssClass`
  We rename automatically the class file and link it to the database.

The header line with all optional columns;

```csv
theme;theme_en;critere;critere-label_en;test;test-label_en;level;scope;class-name
```

### Add multiple languages

The first and second columns are for the themes. The first for the theme number, and the second for the theme name.
We consider the first theme column as the default language. If you want to have multiple languages (i18n), add 
other column suffixes with the lang code (fr, en, es…). Example of header line with multiple languages:
 
 ```csv
 theme;theme_en;theme_fr;test;test-label_en;test-label_fr
 ```

Note that if you have to translate the themes, you must translate the others columns : each language must have a translation for theme, and test-label.

You can see [our CSV example](https://github.com/Asqatasun/Asqatasun/blob/master/rules/rules-creation-demo/src/main/resources/referential-creator-csv-src/referential.csv)
or [another CSV exemple](https://github.com/Asqatasun/Asqatasun/blob/master/rules/referential-creator/src/main/resources/referential/referential.csv).

## Generate your referential (from data)

Get into the referential-context-creator project: 

```sh
cd Asqatasun/rules/referential-creator/
```

Edit the `pom.xml` file and set the properties between the `<properties>` tag.
 
1. In the `<generator.referentialName>` tag, set the full name of your referential (i.e. Rgaa 3.0) 
1. In the `<generator.referential>` tag, set the codename of your referential name with lowercase letters, a version and without whitespace. (i.e. rgaa3.0)
1. In the `<generator.baseFolder>` tag, set the absolute path where you want to install the referential.
1. In the `<generator.dataFile>` tag, set the absolute path to your CSV file.
1. The `<generator.delimiter>` is optional. Set the character separating each CSV columns. (Default value is `;`). 
1. The `<generator.refDescriptor>` is optional, you may set a URL describing your referential.

* Build this project (referential-context-creator):

```sh
mvn referential-creator:generate
```

The referential is now ready.

## The generated referential context

### Rules classes

The Java rules classes files are available under<br/> 
`$my-generated-ref/src/main/java/org/asqatasun/rules/$myRef/`

### Test classes (JUnit) and the testcases (HTML)

The Java test classes files are available under<br/> 
`$my-generated-ref/src/test/java/org/asqatasun/rules/$myRef/`

The HTML testcases files are available under<br/>
`$my-generated-ref/src/test/resources/testcases/$myRef/`

### Sql insertion script

The SQL insertion script file is available under<br/>
`$my-generated-ref/src/main/resources/sql/`

### I18n files

The I18n files are available under<br/>
`$my-generated-ref/src/main/resources/i18n/`

### Spring configuration files (to be rendered in the UI)

The Spring configuration files are available under<br/>
`$my-generated-ref/src/main/resources/conf/`

## How to generate the new referential and make it usable from the Asqatasun web application? 

### Build

```sh
cd $myGeneratedReferential
mvn clean install
cd target
tar xvf tgz
```

### Install in database

```sh
cd tgz-extract-folder/sql/
mysql -u $username -p$myPassword $asqatasunDatabase < $ref-insert.sql
```

### Deploy in the web application context

```sh
cd tgz-extract-folder/
vi deploy.sh ## Set $TOMCAT_HOST_PATH with your tomcat asqatasun web-app folder path. 
./deploy.sh
```

Then restart tomcat

```sh
sudo invoke-rc.d $tomcatVersion restart
```



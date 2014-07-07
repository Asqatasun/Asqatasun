# Referential-Creator helps the referential creation

## Prerequisite : 
Build tanaguru (it may take a while !!)
```sh
cd Tanaguru/
mvn clean install
```

Install manually the apache commons-csv library.
```sh
cd Tanaguru/rules/referential-creator-maven-plugin/src/main/resources/lib/
./install_lib.sh
```

Build the referential-creator maven plugin
```sh
cd Tanaguru/rules/referential-creator-maven-plugin/
mvn clean install
```

## Prepare and generate your referential
### Prepare your referential (CSV File)

#### Mandatory columns
* The theme column : The thematic id, defined by an integer (should be incremented).

* The test column : Defined by two numbers separated by a dash. The first is the theme number and the second is the test number.

The minimum header line to create context : 
`theme;theme_en;test;test-label_en`

#### Optional columns

* The level column : The default value is 1. You can override this value :
 * 1 : high criticality
 * 2 : medium criticality
 * 3 : low critically

* The scope column : The default value is 1.
 * For page rule, set the value to 1.
 * For site rule, set the value to 2.
 * For site and page rule, set the value to 3.

* The class-name column : The Default value use the test code number.
If you want to name your class, set the cell with the class name. i.e. MyClassRuleToTestCssClass<br/>
We rename automatically the class file and link it to the database.

The header line with all optional columns :
`theme;theme_en;critere;critere-label_en;test;test-label_en;level;scope;class-name`

#### Add multiple languages

The first and second columns are for the themes. The first for the theme number, and the second for the theme Name. We consider the first theme column as the default language. If you want to have multi languages (i18n) add other column suffixes by the lang code (fr, en, es, ...)<br/> 
Header line with multiple languages : `theme;theme_en;theme_fr;test;test-label_en;test-label_fr`<br/>
Note that if you have to translate the themes, you must translate the others columns : each language must have a translation for theme, and test-label.

You can see [our CSV example](https://github.com/Tanaguru/Tanaguru/blob/master/rules/rules-creation-demo/src/main/resources/referential-creator-csv-src/referentiel.csv).

### Generate your referential (from data)

* Go to the referentiel-context-creator project.<br/>
```sh
cd Tanaguru/rules/referential-creator/
```
* Edit the pom.xml file and set the properties between the `<properties>` tag. 
 1. In the `<generator.referentielName>` tag, set the complete name of your referential (i.e. Rgaa 3.0) 
 1. In the `<generator.referentiel>` tag, set the of your referential name with lowercase letters, a version and without whitespace. (i.e. rgaa3.0)
 1. In the `<generator.baseFolder>` tag, set the absolute path where you want to install the referential.
 1. In the `<generator.dataFile>` tag, set the absolute path to your CSV file.
 1. In the `<generator.delimiter>` is optional. Set the character who's delimite each CSV columns. (Default value is `;` character). 
 1. In the `<generator.refDescriptor>` is optional, if you have an web page that describe your referential, set this url between refDescriptor tag.

* Build this project (referentiel-context-creator) :
```sh
mvn referential-creator:generate
```

Your referential is now ready to implement what you need !
## The generated referential context
### Rules classes
The Java rules classes files are available under<br/> 
`$my-generated-ref/src/main/java/org/opens/tanaguru/rules/$myRef/`
### Test classes (JUnit) and the testcases (HTML)
The Java test classes files are available under<br/> 
`$my-generated-ref/src/test/java/org/opens/tanaguru/rules/$myRef/`

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

## How to generate the new referential and make it usable from the Tanaguru web application? 
### Build
```sh
cd $my-generated-ref
mvn clean install
cd target
tar xvf tgz
```
### Install in database
```sh
mysql -u $username -p $tanaguru-database < $ref-insert.sql
```
### Deploy in the web application context
# Referential-Creator helps the referential creation

@@@TODO refactor this doc

## Prerequisite : 
Build asqatasun (it may take a while !!)
```sh
cd Asqatasun/

# before first build
mvn install:install-file -DgroupId=com.saucelabs -DartifactId=sebuilder-interpreter -Dversion=1.0.2 -Dpackaging=jar -Dfile=engine/asqatasun-resources/src/main/resources/lib/sebuilder-interpreter-1.0.2.jar

# build
mvn clean install
```

Build the referential-creator maven plugin
```sh
cd Asqatasun/rules/referential-creator-maven-plugin/
mvn clean install
```

## Prepare and generate your referential
### Prepare your referential (CSV File)

#### Mandatory columns
* The theme column : Each cell contain a thematic id, defined by an integer (should be incremented).

* The theme_xx column : Each cell contain the theme label to be display on the UI.

* The test column : Defined by two numbers separated by a dash. The first is the theme number (the number in the first cell on the current line and the second is the test number (for each theme, a test code can't be used many times).

* The test-label_xx column : Each cell contains the test label to be display on the UI.

The minimum header line to create context : 
`theme;theme_en;test;test-label_en`
Note that you can replace `en` by an other language.

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

You can see [our CSV example](https://github.com/Asqatasun/Asqatasun/blob/master/rules/rules-creation-demo/src/main/resources/referential-creator-csv-src/referential.csv)
or [another CSV exemple](https://github.com/Asqatasun/Asqatasun/blob/master/rules/referential-creator/src/main/resources/referential/referential.csv).

### Generate your referential (from data)

* Go to the referential-context-creator project.<br/>
```sh
cd Asqatasun/rules/referential-creator/
```
* Edit the pom.xml file and set the properties between the `<properties>` tag. 
 1. In the `<generator.referentialName>` tag, set the complete name of your referential (i.e. Rgaa 3.0) 
 1. In the `<generator.referential>` tag, set the of your referential name with lowercase letters, a version and without whitespace. (i.e. rgaa3.0)
 1. In the `<generator.baseFolder>` tag, set the absolute path where you want to install the referential.
 1. In the `<generator.dataFile>` tag, set the absolute path to your CSV file.
 1. In the `<generator.delimiter>` is optional. Set the character who's delimite each CSV columns. (Default value is `;` character). 
 1. In the `<generator.refDescriptor>` is optional, if you have an web page that describe your referential, set this url between refDescriptor tag.

* Build this project (referential-context-creator) :
```sh
mvn referential-creator:generate
```

Your referential is now ready to implement what you need !

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



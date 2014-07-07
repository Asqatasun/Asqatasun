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
### The csv file format
The Header line :<br/>
`theme;theme_en;critere;critere-label_en;test;test-label_en;level;scope;class-name`<br/>

The first and second columns are for the themes. The first for the theme number, and the second for the theme Name. The first theme column name define the default language name. If you want to have multi languages (i18n) add other column suffixes by the lang code (fr, en, es, ...)<br/> 
For example<br/>
`theme;theme_en;theme_fr;critere;critere-label_en;critere-label_fr;test;test-label_en;test-label_fr`<br/>
Note that if you have to translate the themes, you must translate the others columns : each language must have a translation for theme, critere-label and test-label.

* The theme column (mandatory) : Define by a number, just an integer.

* The critere column (Optional) : Define by two numbers separated by a dash. The first number is the theme number, the second is the critere number.<br/>

* The test column (mandatory) : Define by three numbers (or two if the critere column is not set) separated by a dash. The first is the theme number. If the critere colum is set, the second is the critere number and the third (or the second if there is not critere column) is the test number.

* The level column (Optional) : The default value is 1. You can override this value :
 * 1 : high criticality
 * 2 : medium criticality
 * 3 : low critically

* The scope column (Optional) : The default value is 1.
 * For page rule, set the value to 1.<br/>
 * For site rule, set the value to 2.<br/>
 * For site and page rule, set the value to 3.<br/>

* The class-name column (Optional) : The Default value use the test code number.<br/>
If you want to name your class, set the cell with the class name. i.e. MyClassRuleToTestCssClass<br/>
We rename automatically the class file and link it to the database.<br/>

You can see [our CSV example](https://github.com/Tanaguru/Tanaguru/blob/master/rules/rules-creation-demo/src/main/resources/referential-creator-csv-src/referentiel.csv).

## The steps to generate the context

1. Go to the referentiel-context-creator project.

1. Edit the pom.xml file and set the properties between the `<properties>` tag. 
 1. In the `<generator.referentielName>` tag, set the complete name of your referential (i.e. Rgaa 3.0) 
 1. In the `<generator.referentiel>` tag, set the of your referential name with lowercase letters, a version and without whitespace. (i.e. rgaa3.0)
 1. In the `<generator.baseFolder>` tag, set the absolute path where you want to install the referential.
 1. In the `<generator.dataFile>` tag, set the absolute path to your CSV file.
 1. In the `<generator.delimiter>` is optional. Set the character who's delimite each CSV columns. (Default value is `;` character). 
 1. In the `<generator.refDescriptor>` is optional, if you have an web page that describe your referential, set this url between refDescriptor tag.
1. Build this project (referentiel-context-creator) with `referential-creator:generate`

Your referential is now ready to implement what you need !
## The generated referential context
### Rules classes
### Test classes (JUnit) and the testcases (HTML)
### Sql insertion script
### I18n files
### Spring configuration files (to be rendered in the UI)
### The testcases

## How to generate the new referential and make it usable from the Tanaguru web application? 
### Build
```shell
cd $my-generated-ref
mvn clean install
cd target
tar xvf tgz
### Install in database
### Deploy in the web application context
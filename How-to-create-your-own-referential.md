# Referential-Creator ease the referential creation

## Prerequisite :
Manual install of the apache commons-csv library available here.
Go into the jar folder and execute this command :

`mvn install:install-file -Dfile=commons-csv-1.0-SNAPSHOT.jar -DgroupId=org.apache -DartifactId=commons-csv -Dversion=1.0-SNAPSHOT -Dpackaging=jar`

## How to format the CSV File ?
The Header line : theme;theme_en;critere;critere-label_en;test;test-label_en
The first and second columns are for the themes. The first for the theme number, and the second for the theme Name. The first theme column name define the default language name. If you want to have multi language (i18n) add other column suffixes by the two letters language (i.e. theme;theme_en;theme_fr;critere;critere-label_en;critere-label_fr;test;test-label_en;test-label_fr).
Note that if you have translate the themes, you must translate the others columns : each language must have a  translate for theme, critere-label and test-label.

The theme column : Define by a number, just an integer.
The critere column : Define by two numbers separated by a dash. The first number is the theme number, the second is the critere number.
The test column : Define by three numbers separated by a dash. The first is the theme number, the second is the critere number and the third is the test number.

## The steps to generate the context

1. Build the referentiel-creator maven plugin.

1. Get the referentiel-context-creator project.

1. Open and edit the pom.xml file and set the properties between the `<properties>` tag. 
 1. In the `<generator.referentielName>` tag, set the complete name of your referential (i.e. Rgaa 3.0) 
 1. In the `<generator.referentiel>` tag, set the of your referential name with lowercase letters, a version and without whitespace. (i.e. rgaa3.0)
 1. In the `<generator.groupId>` tag, set your company groupId (i.e. org.opens)
 1. In the `<generator.baseFolder>` tag, set the absolute path where you want to install the referential.
 1. In the `<generator.dataFile>` tag, set the absolute path to your CSV file.
 1. In the `<generator.delimiter>` is optional. Set the character who's delimite each CSV columns. (Default, the delimiter is set with semicolon `;` character. 
 1. In the `<generator.refDescriptor>` is optional, if you have an URL page that describe your referential, set this url between refDescriptor tag.
1. Build this project (referentiel-context-creator).

Your referential is now ready to implement what you need !
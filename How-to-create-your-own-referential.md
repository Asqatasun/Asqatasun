You can use Tanaguru Referential-Creator to ease the creation of your own referentiel.

Prerequisite :
Manual install of the apache commons-csv library available here.
Go into the jar folder and execute this command :

`mvn install:install-file -Dfile=commons-csv-1.0-SNAPSHOT.jar -DgroupId=org.apache -DartifactId=commons-csv -Dversion=1.0-SNAPSHOT -Dpackaging=jar`

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
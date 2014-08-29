#!/bin/bash

## /!\ This script MUST be in the folder where there are the HTML files 
## to convert. To use this script, you MUST install pandoc software
## http://johnmacfarlane.net/pandoc/installing.html

for n in *.html
do
pandoc $n -f html -o $n.md -t markdown
mv $n.md ${n/.html}.md
done
for i in *.md
do
sed -i ':a;N;$!ba;s/Criterion :\n\n/Criterion : /g' $i
sed -i ':a;N;$!ba;s/Test :\n\n/Test : /g' $i
done
echo "All files are converted"
# Usage of Tanaguru Command Line Interface

To launch tanaguru on URLs, execute the following commands from the tanaguru CLI context folder:

```sh
cd $tanaguruCliContextPath
./bin/tanaguru.sh [OPTIONS]... [URL OR FILE OR SCENARIO]...
```

where [URL OR FILE OR SCENARIO] is a blank-separated list of Urls or Files or Scenario to be tested.

Here is the usage of the script

```
usage: ./bin/tanaguru.sh [OPTIONS]... [URL OR FILE OR SCENARIO]...
                         
 -d,--display <arg>       Value of the display
 -f,--firefox-bin <arg>   Path to the firefox bin
 -h,--help                Show this message.
 -l,--level <arg>         Level :
                          - "Or" for Gold level or AAA level,
                          - "Ar" for Silver level or AA level (default),
                          - "Bz" for Bronze level or A level
 -o,--output <arg>        Path to the output result file.
 -r,--referential <arg>   Referential :
                          - "Aw22" for Accessiweb 2.2 (default)
                          - "Rgaa22" for Rgaa 2.2
                          - "Rgaa30" for Rgaa 3.0
 -t,--audit-type <arg>    Audit type :
                          - "Page" for page audit (default)
                          - "File" for file audit
                          - "Scenario" for scenario audit
                          - "Site" for site audit
 -x,--xmx-value <arg>     Xmx value set to the process (without 'M' at the
                          end):
                          - default is 256
                          - must be superior to 64 (Xms value)

```

To customize the rules set, use the -l option of the script to choose the appropriate level and its associated rule set.

To launch tanaguru on Scenario (recorded from [SeBuilder firefox add-on](http://sebuilder.github.io/se-builder/)), execute the following commands :

```sh
cd $tanaguruCliContextPath
./bin/tanaguru.sh -t Scenario ${path_to_json_file}
```

where $path_to_json_file is a local path to your scenario file (.json).

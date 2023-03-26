# Cron parser

Command line application parses a cron string and expands each field
to show the times at which it will run. Written in Java.

Example input and output:

*/15 0 1,15 * 1-5 /usr/bin/find

Prints following output:

minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find

# Prerequisites

- java 17
- maven

# Bulding jar
 
 Use command mvn package. 
 Jar cronparser-{version}-shaded will be located in target folder.

# Running

Use command java -jar <jar> "argument". Example java -jar .\cronparser-1.0-SNAPSHOT-shaded.jar "*/15 0 1,15 * 1-5 /usr/bin/find"

 


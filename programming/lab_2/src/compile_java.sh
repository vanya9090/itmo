javac -cp Pokemon.jar -d classes {*,*/{*,*/*}}.java
jar xf Pokemon.jar
mkdir -p classes/ru/ifmo/se/
mv ru/ifmo/se/pokemon/ classes/ru/ifmo/se/pokemon/
echo -e "Manifest-Version: 1.0\nMain-Class: Main\n" > MANIFEST.mf
jar -cvfm app.jar MANIFEST.mf -C classes .

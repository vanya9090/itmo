javac -d classes **.java
echo -e "Manifest-Version: 1.0\nMain-Class: Main\n" > MANIFEST.mf
jar -cvfm app.jar MANIFEST.mf -C classes .

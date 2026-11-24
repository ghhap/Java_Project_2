@echo off
cd /d "%~dp0"
javadoc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d docs -sourcepath src/main/java -subpackages cars
pause

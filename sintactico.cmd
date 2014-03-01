cls
cd %~dp0
cd src\sintactico
..\..\tool\yacc -J -v -Jpackage=sintactico -Jsemantic=Object .\sinj.y
cd ..\..
pause

@echo off
echo Compilando o projeto...
if not exist "bin" mkdir bin
javac -cp ".;lib/*" -d bin @sources.txt
if %errorlevel% neq 0 (
    echo Erro na compilacao!
    pause
    exit /b %errorlevel%
)

echo Executando o programa...
java -cp ".;bin;lib/*" src.Main
pause 
@echo off
echo.
echo *******************************************************************************
echo Compilacao do projeto peticwizard
echo *******************************************************************************
echo.
call mvn clean package install
pause
@echo off
:: Executar com o JBoss AS ja iniciado
echo.
echo *******************************************************************************
echo Compilacao do projeto peticwizard
echo *******************************************************************************
echo.
call mvn clean package install
echo.
echo *******************************************************************************
echo Implantacao e execucao do projeto peticwizard-web
echo *******************************************************************************
echo.
cd peticwizard-web
call mvn jboss-as:deploy
cd ..
pause
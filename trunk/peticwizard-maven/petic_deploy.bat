@echo off
:: Executar depois de compilar a aplicacao, com o JBoss AS ja iniciado
echo.
echo *******************************************************************************
echo Implantacao e execucao do projeto peticwizard-web
echo *******************************************************************************
echo.
cd peticwizard-web
call mvn jboss-as:deploy
echo.
cd ..
echo *******************************************************************************
echo Implantacao e execucao do projeto peticwizard-services
echo *******************************************************************************
echo.
cd peticwizard-services
call mvn jboss-as:deploy
echo.
cd ..
pause
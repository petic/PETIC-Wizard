#!/bin/bash
#Executar com o JBoss AS ja iniciado
echo "*******************************************************************************"
echo "Compilacao do projeto peticwizard"
echo "*******************************************************************************"
mvn clean package install
echo "*******************************************************************************"
echo "Implantacao e execucao do projeto peticwizard-web"
echo "*******************************************************************************"
cd peticwizard-web
mvn jboss-as:deploy
cd ..
echo "*******************************************************************************"
echo "Implantacao e execucao do projeto peticwizard-services"
echo "*******************************************************************************"
cd peticwizard-services
mvn jboss-as:deploy
cd ..
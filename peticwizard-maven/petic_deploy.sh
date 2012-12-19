#!/bin/bash
#Executar depois de compilar a aplicacao, com o JBoss AS ja iniciado
echo "*******************************************************************************"
echo "Implantacao e execucao do projeto peticwizard-web"
echo -e "*******************************************************************************\n"
cd peticwizard-web
mvn jboss-as:deploy
cd ..
echo "*******************************************************************************"
echo "Implantacao e execucao do projeto peticwizard-services"
echo -e "*******************************************************************************\n"
cd peticwizard-services
mvn jboss-as:deploy
cd ..
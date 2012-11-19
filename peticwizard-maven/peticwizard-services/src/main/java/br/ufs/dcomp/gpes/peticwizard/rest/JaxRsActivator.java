package br.ufs.dcomp.gpes.peticwizard.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Classe respons�vel por inicializar o servi�o.
 * 
 * Uma classe que estende {@link Application} e � anotada com
 * {@link ApplicationPath} � o m�todo alternativo do Java EE 6 para ativar
 * servi�os JAX-RS sem configura��o por XML. Os servi�os dessa aplica��o
 * respondem pela URL especificado na anota��o <code>@ApplicationPath</code>,
 * que nesse caso � <code>/</code>.
 */
@ApplicationPath("/")
public class JaxRsActivator extends Application {
	// Corpo de classe deixado em branco intencionalmente.
}
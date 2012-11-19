package br.ufs.dcomp.gpes.peticwizard.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Classe responsável por inicializar o serviço.
 * 
 * Uma classe que estende {@link Application} e é anotada com
 * {@link ApplicationPath} é o método alternativo do Java EE 6 para ativar
 * serviços JAX-RS sem configuração por XML. Os serviços dessa aplicação
 * respondem pela URL especificado na anotação <code>@ApplicationPath</code>,
 * que nesse caso é <code>/</code>.
 */
@ApplicationPath("/")
public class JaxRsActivator extends Application {
	// Corpo de classe deixado em branco intencionalmente.
}
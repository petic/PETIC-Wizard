package br.ufs.dcomp.gpes.peticwizard.presentation.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 * Classe auxiliar cujos m�todos facilitam a cria��o e adi��o de mensagens (do
 * tipo {@link FacesMessage}) ao contexto ({@link FacesContext}) da requisi��o
 * para que sejam exibidas na pr�xima p�gina (view) a ser renderizada para o
 * usu�rio (que pode ser a mesma na qual ele est� ou outra). Cada mensagem pode
 * ter diferentes n�veis de severidade, configurando mensagens de erro (
 * <code>FacesMessage.SEVERITY_ERROR</code>) ou informa��o (
 * <code>FacesMessage.SEVERITY_INFO</code>), por exemplo.
 * 
 * @see FacesContext
 * @see FacesMessage
 */

public class JSFMessages {

	/**
	 * Define que a <code>mensagem</code> deve ser exibida para o usu�rio na
	 * mesma p�gina (view) em que ele se encontra.
	 * 
	 * @param severidade
	 *            uma constante do tipo {@link Severity} representando a
	 *            <code>severidade</code> da mensagem a ser exibida. Pode ser
	 *            erro (<code>FacesMessage.SEVERITY_ERROR</code>) ou informa��o
	 *            (<code>FacesMessage.SEVERITY_INFO</code>), por exemplo.
	 * 
	 * @param mensagem
	 *            um objeto do tipo {link String} contendo o texto da mensagem a
	 *            ser exibida.
	 */

	public static void exibirMensagem(Severity severidade, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severidade, mensagem, null));
	}

	/**
	 * Configura a <code>mensagem</code> para ser exibida na pr�xima p�gina
	 * (view) a ser renderizada para o usu�rio.
	 * 
	 * @param severidade
	 *            uma constante do tipo {@link Severity} representando a
	 *            <code>severidade</code> da mensagem a ser exibida. Pode ser
	 *            erro (<code>FacesMessage.SEVERITY_ERROR</code>) ou informa��o
	 *            (<code>FacesMessage.SEVERITY_INFO</code>), por exemplo.
	 * 
	 * @param mensagem
	 *            um objeto do tipo {link String} contendo o texto da mensagem a
	 *            ser exibida.
	 */

	public static void exibirMensagemAposRedirecionar(Severity severidade,
			String mensagem) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		exibirMensagem(severidade, mensagem);
	}
}
package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * {@link ManagedBean} responsável por controlar a navegação entre páginas
 * iniciais da aplicação, apresenta escopo de aplicação (
 * {@link ApplicationScoped}).
 * 
 * O termo "escopo de aplicação" significa que enquanto a aplicação estiver
 * sendo executada, esse managed bean permanecerá armazenado na memória, sendo
 * destruído somente quando a aplicação for encerrada.
 * 
 * @see ManagedBean
 * @see ApplicationScoped
 */

@ManagedBean
@ApplicationScoped
public class NavegacaoBean {

	public String irParaPaginaInicial() {
		return "/index.xhtml?faces-redirect=true";
	}

	public String abrirCatalogoDeProcessosGenericos() {
		return "/catalogoDeProcessos/index.xhtml?faces-redirect=true";
	}

	public String abrirRepositorioDeAcoes() {
		return "/repositorioDeAcoes/index.xhtml?faces-redirect=true";
	}

}
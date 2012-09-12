package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * {@link ManagedBean} respons�vel por controlar a navega��o entre p�ginas
 * iniciais da aplica��o, apresenta escopo de aplica��o (
 * {@link ApplicationScoped}).
 * 
 * O termo "escopo de aplica��o" significa que enquanto a aplica��o estiver
 * sendo executada, esse managed bean permanecer� armazenado na mem�ria, sendo
 * destru�do somente quando a aplica��o for encerrada.
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
package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AcaoGenericaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} respons�vel por controlar a view
 * "/repositorioDeAcoes/remocao.xhtml", apresenta escopo de vis�o (
 * {@link ViewScoped}).
 * 
 * O termo "escopo de vis�o" significa que enquanto o usu�rio estiver
 * visualizando essa view (com ela aberta no navegador), os objetos utilizados
 * por ela permanecem armazenados no servidor. Esses objetos s�o destru�dos
 * assim que o usu�rio navega dessa view para outra.
 * 
 * @see ManagedBean
 * @see ViewScoped
 */

@ManagedBean
@ViewScoped
public class RemocaoDeAcaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idDaAcao;

	private AcaoGenerica acaoGenerica;

	@EJB
	private AcaoGenericaDAO daoAcaoGenerica;

	public void carregarAcao() {
		if (idDaAcao == null) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Requisi��o inv�lida!");
			return;
		}
		try {
			acaoGenerica = daoAcaoGenerica.buscar(Integer.parseInt(idDaAcao));
		} catch (Exception excecao) {
			acaoGenerica = null;
		}
		if (acaoGenerica == null) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"N�o existe a��o com a ID fornecida como par�metro!");
			return;
		}
	}

	public String remover() {
		try {
			daoAcaoGenerica.remover(acaoGenerica);
			JSFMessages.exibirMensagemAposRedirecionar(
					FacesMessage.SEVERITY_INFO,
					"A a��o foi removida com sucesso!");
			return "/repositorioDeAcoes/index.xhtml?faces-redirect=true";
		} catch (Exception excecao) {
			JSFMessages.exibirMensagemAposRedirecionar(
					FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da remo��o.");
			return "/repositorioDeAcoes/index.xhtml?faces-redirect=true";
		}
	}

	public String getIdDaAcao() {
		return idDaAcao;
	}

	public AcaoGenerica getAcaoGenerica() {
		return acaoGenerica;
	}

	public void setIdDaAcao(String idDaAcao) {
		this.idDaAcao = idDaAcao;
	}

	public void setAcaoGenerica(AcaoGenerica acaoGenerica) {
		this.acaoGenerica = acaoGenerica;
	}

}
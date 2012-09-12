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
 * {@link ManagedBean} responsável por controlar a view
 * "/repositorioDeAcoes/remocao.xhtml", apresenta escopo de visão (
 * {@link ViewScoped}).
 * 
 * O termo "escopo de visão" significa que enquanto o usuário estiver
 * visualizando essa view (com ela aberta no navegador), os objetos utilizados
 * por ela permanecem armazenados no servidor. Esses objetos são destruídos
 * assim que o usuário navega dessa view para outra.
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
					"Requisição inválida!");
			return;
		}
		try {
			acaoGenerica = daoAcaoGenerica.buscar(Integer.parseInt(idDaAcao));
		} catch (Exception excecao) {
			acaoGenerica = null;
		}
		if (acaoGenerica == null) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Não existe ação com a ID fornecida como parâmetro!");
			return;
		}
	}

	public String remover() {
		try {
			daoAcaoGenerica.remover(acaoGenerica);
			JSFMessages.exibirMensagemAposRedirecionar(
					FacesMessage.SEVERITY_INFO,
					"A ação foi removida com sucesso!");
			return "/repositorioDeAcoes/index.xhtml?faces-redirect=true";
		} catch (Exception excecao) {
			JSFMessages.exibirMensagemAposRedirecionar(
					FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da remoção.");
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
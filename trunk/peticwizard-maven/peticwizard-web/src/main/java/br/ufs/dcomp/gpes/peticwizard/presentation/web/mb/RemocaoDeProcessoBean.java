package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} responsável por controlar a view
 * "/catalogoDeProcessos/remocao.xhtml", apresenta escopo de visão (
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
public class RemocaoDeProcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idDoProcesso;

	private ProcessoGenerico processo;

	@EJB
	private ProcessoGenericoDAO daoProcessoGenerico;

	public void carregarProcesso() {
		if (idDoProcesso == null) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Requisição inválida!");
			return;
		}
		try {
			processo = daoProcessoGenerico.buscar(Integer
					.parseInt(idDoProcesso));
		} catch (Exception excecao) {
			processo = null;
		}
		if (processo == null) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Não existe processo com a ID fornecida como parâmetro!");
			return;
		}
	}

	public String remover() {
		try {
			daoProcessoGenerico.remover(processo);
			JSFMessages.exibirMensagemAposRedirecionar(
					FacesMessage.SEVERITY_INFO,
					"O processo foi removido com sucesso!");
			return "/catalogoDeProcessos/index.xhtml?faces-redirect=true";
		} catch (Exception excecao) {
			JSFMessages.exibirMensagemAposRedirecionar(
					FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da remoção.");
			return "/catalogoDeProcessos/index.xhtml?faces-redirect=true";
		}
	}

	public String getIdDoProcesso() {
		return idDoProcesso;
	}

	public ProcessoGenerico getProcesso() {
		return processo;
	}

	public void setIdDoProcesso(String idDoProcesso) {
		this.idDoProcesso = idDoProcesso;
	}

	public void setProcesso(ProcessoGenerico processo) {
		this.processo = processo;
	}

}
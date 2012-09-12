package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} responsável por controlar a view
 * "/catalogoDeProcessos/processo.xhtml", apresenta escopo de requisição (
 * {@link RequestScoped}).
 * 
 * O termo "escopo de requisição" significa que enquanto a requisição estiver
 * sendo processada (do momento em que o servidor recebe a requisição até o
 * momento em que a resposta da requisição é enviada ao usuário), os objetos
 * utilizados pelo managed bean permanecem armazenados no servidor. Esses
 * objetos são destruídos assim que a resposta é enviada.
 * 
 * @see ManagedBean
 * @see ViewScoped
 */

@ManagedBean
@RequestScoped
public class VisualizacaoDeProcessoBean {

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
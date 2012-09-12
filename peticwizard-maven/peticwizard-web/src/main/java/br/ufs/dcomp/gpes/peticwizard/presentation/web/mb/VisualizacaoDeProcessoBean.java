package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} respons�vel por controlar a view
 * "/catalogoDeProcessos/processo.xhtml", apresenta escopo de requisi��o (
 * {@link RequestScoped}).
 * 
 * O termo "escopo de requisi��o" significa que enquanto a requisi��o estiver
 * sendo processada (do momento em que o servidor recebe a requisi��o at� o
 * momento em que a resposta da requisi��o � enviada ao usu�rio), os objetos
 * utilizados pelo managed bean permanecem armazenados no servidor. Esses
 * objetos s�o destru�dos assim que a resposta � enviada.
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
					"Requisi��o inv�lida!");
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
					"N�o existe processo com a ID fornecida como par�metro!");
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
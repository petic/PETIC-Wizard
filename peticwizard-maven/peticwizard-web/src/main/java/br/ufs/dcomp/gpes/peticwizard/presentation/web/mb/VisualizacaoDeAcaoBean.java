package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AcaoGenericaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} respons�vel por controlar a view
 * "/repositorioDeAcoes/acao.xhtml", apresenta escopo de requisi��o (
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
public class VisualizacaoDeAcaoBean {

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
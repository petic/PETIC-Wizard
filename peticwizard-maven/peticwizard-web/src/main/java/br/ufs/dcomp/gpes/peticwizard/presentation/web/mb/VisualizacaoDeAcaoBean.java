package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AcaoGenericaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} responsável por controlar a view
 * "/repositorioDeAcoes/acao.xhtml", apresenta escopo de requisição (
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
public class VisualizacaoDeAcaoBean {

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
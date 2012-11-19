package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AcaoGenericaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.presentation.web.util.JSFMessages;

/**
 * {@link ManagedBean} respons�vel por controlar a view
 * "/repositorioDeAcoes/cadastro.xhtml", apresenta escopo de vis�o (
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
public class CadastroDeAcaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean cadastro = false;

	private String idDaAcao;

	private AcaoGenerica acaoGenerica;

	@EJB
	private AcaoGenericaDAO daoAcaoGenerica;

	@EJB
	private ProcessoGenericoDAO daoProcessoGenerico;

	public void preRenderView() {
		if (acaoGenerica == null) {
			if (idDaAcao != null) {
				try {
					acaoGenerica = daoAcaoGenerica.buscar(idDaAcao);
				} catch (Exception excecao) {
					acaoGenerica = null;
				}
				if (acaoGenerica == null) {
					JSFMessages
							.exibirMensagem(FacesMessage.SEVERITY_ERROR,
									"N�o existe a��o com a ID fornecida como par�metro!");
					return;
				}
			} else {
				acaoGenerica = new AcaoGenerica();
				cadastro = true;
			}
		}
	}

	public String salvar() {
		try {
			System.out.println("\n\n\nSALVAR!");
			System.out.println("Processo selecionado: "
					+ acaoGenerica.getProcessoGenerico().getNome());
			acaoGenerica.setProcessoGenerico(daoProcessoGenerico
					.buscar(acaoGenerica.getProcessoGenerico().getId()));
			if (cadastro) {
				System.out.println("Cadastro!!!");
				daoAcaoGenerica.inserir(acaoGenerica);
				JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"A a��o foi adicionada com sucesso!");
			} else {
				System.out.println("Edi��o!!!");
				daoAcaoGenerica.atualizar(acaoGenerica);
				JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"A a��o foi alterada com sucesso!");
			}
			System.out.println("\n\n\n");
			return "/repositorioDeAcoes/index.xhtml?faces-redirect=true";
		} catch (Exception excecao) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da solicita��o.");
			return "cadastro.xhtml";
		}
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public String getIdDaAcao() {
		return idDaAcao;
	}

	public AcaoGenerica getAcaoGenerica() {
		return acaoGenerica;
	}

	public List<SelectItem> getListaDeProcessos() {
		List<SelectItem> listaDeProcessos = new ArrayList<SelectItem>();
		for (ProcessoGenerico processo : daoProcessoGenerico.listarTodos()) {
			listaDeProcessos.add(new SelectItem(processo, processo.getNome()));
		}
		return listaDeProcessos;
	}

	public void setIdDaAcao(String idDaAcao) {
		this.idDaAcao = idDaAcao;
	}

	public void setAcaoGenerica(AcaoGenerica acaoGenerica) {
		this.acaoGenerica = acaoGenerica;
	}

}
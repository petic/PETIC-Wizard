package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AreaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.dao.SubareaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Area;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Subarea;
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
public class CadastroDeProcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean cadastro = false;

	private String idDoProcesso;

	private ProcessoGenerico processo;

	@EJB
	private AreaDAO daoArea;

	private Area areaSelecionada;

	@EJB
	private ProcessoGenericoDAO daoProcessoGenerico;

	@EJB
	private SubareaDAO daoSubarea;

	public void atualizarListaDeSubareas(ValueChangeEvent evento) {
		try { // Simula��o de carregamento
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n\nMUDAN�A DE �REA!");
		if (evento.getNewValue() != null) {
			areaSelecionada = daoArea.inserirOuObterArea(((Area) evento
					.getNewValue()).getId());
			System.out.println("�rea: " + areaSelecionada.getDescricao());
		}
	}

	public void preRenderView() {
		if (processo == null) {
			if (idDoProcesso != null) {
				try {
					processo = daoProcessoGenerico.buscar(idDoProcesso);
					areaSelecionada = processo.getSubarea().getArea();
				} catch (Exception excecao) {
					processo = null;
				}
				if (processo == null) {
					JSFMessages
							.exibirMensagem(FacesMessage.SEVERITY_ERROR,
									"N�o existe processo com a ID fornecida como par�metro!");
					return;
				}
			} else {
				processo = new ProcessoGenerico();
				cadastro = true;
			}
		}
	}

	public String salvar() {
		try {
			System.out.println("\n\n\nSALVAR!");
			System.out.println("Sub�rea selecionada: "
					+ processo.getSubarea().getDescricao());
			processo.setSubarea(daoSubarea.buscar(processo.getSubarea().getId()));
			if (cadastro) {
				System.out.println("Cadastro!!!");
				daoProcessoGenerico.inserir(processo);
				JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"O processo foi adicionado com sucesso!");
			} else {
				System.out.println("Edi��o!!!");
				daoProcessoGenerico.atualizar(processo);
				JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"O processo foi alterado com sucesso!");
			}
			System.out.println("\n\n\n");
			return "/catalogoDeProcessos/index.xhtml?faces-redirect=true";
		} catch (Exception excecao) {
			JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da solicita��o.");
			return "cadastro.xhtml";
		}
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public String getIdDoProcesso() {
		return idDoProcesso;
	}

	public ProcessoGenerico getProcesso() {
		return processo;
	}

	public Area getAreaSelecionada() {
		return areaSelecionada;
	}

	public List<SelectItem> getListaDeAreas() {
		List<SelectItem> listaDeAreas = new ArrayList<SelectItem>();
		for (Area area : daoArea.listarTodas()) {
			listaDeAreas.add(new SelectItem(area, area.getDescricao()));
		}
		return listaDeAreas;
	}

	public List<SelectItem> getListaDeSubareas() {
		List<SelectItem> listaDeSubareas = new ArrayList<SelectItem>();
		if (areaSelecionada != null) {
			for (Subarea subarea : areaSelecionada.getSubareas()) {
				listaDeSubareas.add(new SelectItem(subarea, subarea
						.getDescricao()));
			}
		}
		return listaDeSubareas;
	}

	public void setIdDoProcesso(String idDoProcesso) {
		this.idDoProcesso = idDoProcesso;
	}

	public void setProcesso(ProcessoGenerico processo) {
		this.processo = processo;
	}

	public void setAreaSelecionada(Area areaSelecionada) {
		this.areaSelecionada = areaSelecionada;
	}

}
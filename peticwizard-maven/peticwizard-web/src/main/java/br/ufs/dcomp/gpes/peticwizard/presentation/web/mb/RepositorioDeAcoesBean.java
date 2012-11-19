package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.richfaces.component.UIExtendedDataTable;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AcaoGenericaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;

/**
 * {@link ManagedBean} responsável por controlar a view
 * "/repositorioDeAcoes/index.xhtml", apresenta escopo de visão (
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
public class RepositorioDeAcoesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AcaoGenericaDAO daoAcaoGenerica;

	private Collection<Object> selecao;

	private AcaoGenerica acaoSelecionada = null;

	public void atualizarAcaoSelecionada(AjaxBehaviorEvent evento) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) evento
				.getComponent();
		Object linhaAtual = dataTable.getRowKey();
		acaoSelecionada = null;
		for (Object linhaSelecionada : selecao) {
			dataTable.setRowKey(linhaSelecionada);
			if (dataTable.isRowAvailable()) {
				acaoSelecionada = (AcaoGenerica) dataTable.getRowData();
				System.out.println("Ação selecionada: "
						+ acaoSelecionada.getNome());
			}
		}
		dataTable.setRowKey(linhaAtual);
	}

	public Collection<Object> getSelecao() {
		return selecao;
	}

	public AcaoGenerica getAcaoSelecionada() {
		return this.acaoSelecionada;
	}

	public List<AcaoGenerica> getListaDeAcoes() {
		return daoAcaoGenerica.listarTodas();
	}

	public void setSelecao(Collection<Object> selecao) {
		this.selecao = selecao;
	}

}
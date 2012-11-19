package br.ufs.dcomp.gpes.peticwizard.presentation.web.mb;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.richfaces.component.UIExtendedDataTable;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;

/**
 * {@link ManagedBean} responsável por controlar a view
 * "/catalogoDeProcessos/index.xhtml", apresenta escopo de visão (
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
public class CatalogoDeProcessosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessoGenericoDAO daoProcessoGenerico;

	private Collection<Object> selecao;

	private ProcessoGenerico processoSelecionado = null;

	public Collection<Object> getSelecao() {
		return selecao;
	}

	public void setSelecao(Collection<Object> selecao) {
		this.selecao = selecao;
	}

	public List<ProcessoGenerico> getListaDeProcessos() {
		return daoProcessoGenerico.listarTodos();
	}

	public ProcessoGenerico getProcessoSelecionado() {
		return this.processoSelecionado;
	}

	public void atualizarProcessoSelecionado(AjaxBehaviorEvent evento) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) evento
				.getComponent();
		Object linhaAtual = dataTable.getRowKey();
		processoSelecionado = null;
		for (Object linhaSelecionada : selecao) {
			dataTable.setRowKey(linhaSelecionada);
			if (dataTable.isRowAvailable()) {
				processoSelecionado = (ProcessoGenerico) dataTable.getRowData();
				System.out.println("Processo selecionado: "
						+ processoSelecionado.getNome());
			}
		}
		dataTable.setRowKey(linhaAtual);
	}

}
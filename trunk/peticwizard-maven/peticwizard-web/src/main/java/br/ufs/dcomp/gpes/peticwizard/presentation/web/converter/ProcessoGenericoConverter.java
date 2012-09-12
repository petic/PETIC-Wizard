package br.ufs.dcomp.gpes.peticwizard.presentation.web.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;

/**
 * Conversor JSF que deve ser utilizado quando for necessário converter uma
 * entidade {@link ProcessoGenerico} em uma @{link String} única que a
 * identifique ou vice-versa (isso pode acontecer quando se deseja listar vários
 * processos genéricos em um componente <code><h:selectOneMenu></code>, por
 * exemplo).
 * 
 * <p>
 * Referenciar esse conversor nas páginas JSF como se referencia um
 * {@link ManagedBean}, como em: <code>#{processoGenericoConverter}</code>
 * </p>
 * 
 * <p>
 * Conversores JSF não recebem injeção de EJBs do servidor como recebem os
 * managed beans. Por isso é necessário utilizar esse conversor como se fosse um
 * {@link ManagedBean}: para que ele receba um objeto
 * {@link ProcessoGenericoDAO} injetado e seja capaz de, utilizando esse objeto,
 * obter do banco de dados a entidade <code>ProcessoGenerico</code> associada a
 * determinada <code>String</code> (que, nesse caso, deve representar a
 * <code>id</code> de um processo genérico).
 * </p>
 * 
 * @see FacesConverter
 * @see ManagedBean
 * @see ProcessoGenerico
 * @see ProcessoGenericoDAO
 */

@ManagedBean
@FacesConverter(value = "processoGenericoConverter")
public class ProcessoGenericoConverter implements Converter {

	private static final String MENSAGEM_DE_ERRO = "Erro durante a conversão do processo!";

	@EJB
	ProcessoGenericoDAO daoProcessoGenerico;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ProcessoGenerico processo = null;
		try {
			processo = daoProcessoGenerico.buscar(Integer.parseInt(arg2));
		} catch (Throwable ex) {
			FacesMessage facesMessage = new FacesMessage(MENSAGEM_DE_ERRO);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(facesMessage);
		}
		return processo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String valor = null;
		if (arg2 != null) {
			try {
				ProcessoGenerico processo = (ProcessoGenerico) arg2;
				valor = Integer.toString(processo.getId());
			} catch (Throwable ex) {
				FacesMessage facesMessage = new FacesMessage(MENSAGEM_DE_ERRO);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ConverterException(facesMessage);
			}
		}
		return valor;
	}

}
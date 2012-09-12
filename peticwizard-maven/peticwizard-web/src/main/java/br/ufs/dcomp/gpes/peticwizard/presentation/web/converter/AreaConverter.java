package br.ufs.dcomp.gpes.peticwizard.presentation.web.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AreaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Area;

/**
 * Conversor JSF que deve ser utilizado quando for necessário converter uma
 * entidade {@link Area} em uma @{link String} única que a identifique ou
 * vice-versa (isso pode acontecer quando se deseja listar várias áreas em um
 * componente <code><h:selectOneMenu></code>, por exemplo).
 * 
 * <p>
 * Referenciar esse conversor nas páginas JSF como se referencia um
 * {@link ManagedBean}, como em: <code>#{areaConverter}</code>
 * </p>
 * 
 * <p>
 * Conversores JSF não recebem injeção de EJBs do servidor como recebem os
 * managed beans. Por isso é necessário utilizar esse conversor como se fosse um
 * {@link ManagedBean}: para que ele receba um objeto {@link AreaDAO} injetado e
 * seja capaz de, utilizando esse objeto, obter do banco de dados a entidade
 * <code>Area</code> associada a determinada <code>String</code> (que, nesse
 * caso, deve representar a <code>id</code> de uma área).
 * </p>
 * 
 * @see Area
 * @see AreaDAO
 * @see FacesConverter
 * @see ManagedBean
 */

@ManagedBean
@FacesConverter(value = "areaConverter")
public class AreaConverter implements Converter {

	private static final String MENSAGEM_DE_ERRO = "Erro durante a conversão da área!";

	@EJB
	AreaDAO daoArea;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Area area = null;
		try {
			area = daoArea.inserirOuObterArea(Integer.parseInt(arg2));
		} catch (Throwable ex) {
			FacesMessage facesMessage = new FacesMessage(MENSAGEM_DE_ERRO);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(facesMessage);
		}
		return area;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String valor = null;
		if (arg2 != null) {
			try {
				Area area = (Area) arg2;
				valor = Integer.toString(area.getId());
			} catch (Throwable ex) {
				FacesMessage facesMessage = new FacesMessage(MENSAGEM_DE_ERRO);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ConverterException(facesMessage);
			}
		}
		return valor;
	}

}
package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Subarea;

/**
 * DAO (sigla de Data Access Object) � um padr�o de projeto (<i>design
 * pattern</i>) que define a separa��o das regras de neg�cio das regras de
 * acesso a banco de dados. Todas as opera��es referentes a bancos de dados,
 * tais como inser��o, consulta, atualiza��o, remo��o e listagem de registros
 * s�o realizadas por classes projetadas espec�ficas para execut�-las.
 * 
 * <p>
 * Essa classe representa um DAO espec�fico para a entidade {@link Subarea}. Um
 * objeto dessa classe deve ser utilizado sempre que for necess�rio realizar
 * opera��es referentes a banco envolvendo um objeto da classe
 * <code>Subarea</code>.
 * </p>
 * 
 * @see Subarea
 */

@Stateless
public class SubareaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Construtor padr�o da classe {@link SubareaDAO}. Retorna um objeto da
	 * classe <code>SubareaDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes � entidade {@link Subarea}.
	 * 
	 * @deprecated Deve-se utilizar inje��o para construir objetos dessa classe,
	 *             como em: <code>@EJB private SubareaDAO subareaDao;</code>
	 * 
	 * @see EJB
	 * @see Subarea
	 */

	@Deprecated
	public SubareaDAO() {
	}

	/**
	 * Busca no banco de dados uma sub�rea com o <code>id</code> fornecido como
	 * argumento e retorna um objeto da classe {@link Subarea} que representa
	 * essa sub�rea. Pode retornar <code>null</code>, caso n�o exista uma
	 * sub�rea com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            uma {@link String} representando o <code>id</code> da sub�rea
	 *            que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Subarea</code> representando a sub�rea
	 *         desejada, caso ela exista, ou <code>null</code>, caso n�o.
	 */

	public Subarea buscar(String id) {
		TypedQuery<Subarea> query = entityManager.createNamedQuery(
				Subarea.BUSCAR, Subarea.class);
		query.setParameter("idFormatado", id);
		return query.getSingleResult();
	}

}
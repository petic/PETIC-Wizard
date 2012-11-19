package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Subarea;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade {@link Subarea}. Um
 * objeto dessa classe deve ser utilizado sempre que for necessário realizar
 * operações referentes a banco envolvendo um objeto da classe
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
	 * Construtor padrão da classe {@link SubareaDAO}. Retorna um objeto da
	 * classe <code>SubareaDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link Subarea}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em: <code>@EJB private SubareaDAO subareaDao;</code>
	 * 
	 * @see EJB
	 * @see Subarea
	 */

	@Deprecated
	public SubareaDAO() {
	}

	/**
	 * Busca no banco de dados uma subárea com o <code>id</code> fornecido como
	 * argumento e retorna um objeto da classe {@link Subarea} que representa
	 * essa subárea. Pode retornar <code>null</code>, caso não exista uma
	 * subárea com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            uma {@link String} representando o <code>id</code> da subárea
	 *            que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Subarea</code> representando a subárea
	 *         desejada, caso ela exista, ou <code>null</code>, caso não.
	 */

	public Subarea buscar(String id) {
		TypedQuery<Subarea> query = entityManager.createNamedQuery(
				Subarea.BUSCAR, Subarea.class);
		query.setParameter("idFormatado", id);
		return query.getSingleResult();
	}

}
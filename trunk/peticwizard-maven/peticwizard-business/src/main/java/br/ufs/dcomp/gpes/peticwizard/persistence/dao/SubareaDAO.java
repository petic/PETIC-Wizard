package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	 * Insere no banco de dados a <code>sub�rea</code> fornecida como argumento.
	 * 
	 * @param subarea
	 *            um objeto da classe {@link Subarea} representando a sub�rea
	 *            que deve ser inserida no banco de dados.
	 */

	public Subarea inserir(Subarea subarea) {
		entityManager.persist(subarea);
		return subarea;
	}

	/**
	 * Busca no banco de dados uma sub�rea com o <code>id</code> fornecido como
	 * argumento e retorna um objeto da classe {@link Subarea} que representa
	 * essa sub�rea. Pode retornar <code>null</code>, caso n�o exista uma
	 * sub�rea com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            um objeto da classe {@link Integer} (ou um valor do tipo
	 *            <code>int</code>) representando o <code>id</code> da sub�rea
	 *            que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Subarea</code> representando a sub�rea
	 *         desejada, caso ela exista, ou <code>null</code>, caso n�o.
	 */

	public Subarea buscar(Integer id) {
		return entityManager.find(Subarea.class, id);
	}

	/**
	 * Atualiza no banco de dados a <code>sub�rea</code> fornecida como
	 * argumento.
	 * 
	 * @param subarea
	 *            um objeto da classe {@link Subarea} representando a sub�rea
	 *            que deve ser atualizada no banco de dados.
	 */

	public void atualizar(Subarea subarea) {
		entityManager.merge(subarea);
	}

	/**
	 * Remove do banco de dados a <code>sub�rea</code> fornecida como argumento.
	 * 
	 * @param subarea
	 *            um objeto da classe {@link Subarea} representando a sub�rea
	 *            que deve ser removida do banco de dados.
	 */

	public void remover(Subarea subarea) {
		entityManager.remove(entityManager.find(Subarea.class, subarea));
	}

}
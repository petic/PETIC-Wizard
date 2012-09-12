package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade {@link AcaoGenerica}
 * . Um objeto dessa classe deve ser utilizado sempre que for necessário
 * realizar operações referentes a banco envolvendo um objeto da classe
 * <code>AcaoGenerica</code>.
 * </p>
 * 
 * @see AcaoGenerica
 */

@Stateless
public class AcaoGenericaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Construtor padrão da classe {@link AcaoGenericaDAO}. Retorna um objeto da
	 * classe <code>AcaoGenericaDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link AcaoGenerica}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private AcaoGenericaDAO daoAcaoGenerica;</code>
	 * 
	 * @see AcaoGenerica
	 * @see EJB
	 */

	@Deprecated
	public AcaoGenericaDAO() {
	}

	/**
	 * Insere no banco de dados a <code>ação genérica</code> fornecida como
	 * argumento.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a ação
	 *            genérica que deve ser inserida no banco de dados.
	 */

	public AcaoGenerica inserir(AcaoGenerica acaoGenerica) {
		entityManager.persist(acaoGenerica);
		return acaoGenerica;
	}

	/**
	 * Busca no banco de dados uma ação genérica com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link AcaoGenerica} que
	 * representa essa ação. Pode retornar <code>null</code>, caso não exista
	 * uma ação genérica com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            um objeto da classe {@link Integer} (ou um valor do tipo
	 *            <code>int</code>) representando o <code>id</code> da ação
	 *            genérica que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>AcaoGenerica</code> representando a
	 *         ação genérica desejada, caso ela exista, ou <code>null</code>,
	 *         caso não.
	 */

	public AcaoGenerica buscar(Integer id) {
		return entityManager.find(AcaoGenerica.class, id);
	}

	/**
	 * Atualiza no banco de dados a <code>ação genérica</code> fornecida como
	 * argumento.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a ação
	 *            genérica que deve ser atualizada no banco de dados.
	 */

	public void atualizar(AcaoGenerica acaoGenerica) {
		entityManager.merge(acaoGenerica);
	}

	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link AcaoGenerica}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>AcaoGenerica</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see AcaoGenerica
	 * @see List
	 */

	public List<AcaoGenerica> listarTodas() {
		return entityManager.createNamedQuery(AcaoGenerica.LISTAR_TODAS,
				AcaoGenerica.class).getResultList();
	}

	/**
	 * Remove do banco de dados a <code>ação genérica</code> fornecida como
	 * argumento.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a ação
	 *            genérica que deve ser removida do banco de dados.
	 */

	public void remover(AcaoGenerica acaoGenerica) {
		entityManager.remove(entityManager.find(AcaoGenerica.class,
				acaoGenerica.getId()));
	}

}
package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link ProcessoGenerico}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>ProcessoGenerico</code>.
 * </p>
 * 
 * @see ProcessoGenerico
 */

@Stateless
public class ProcessoGenericoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Construtor padrão da classe {@link ProcessoGenericoDAO}. Retorna um
	 * objeto da classe <code>ProcessoGenericoDAO</code> capaz de gerenciar no
	 * banco de dados objetos correspondentes à entidade
	 * {@link ProcessoGenerico}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private ProcessoGenericoDAO processoGenericoDao;</code>
	 * 
	 * @see EJB
	 * @see ProcessoGenerico
	 */

	@Deprecated
	public ProcessoGenericoDAO() {
	}

	/**
	 * Insere no banco de dados o <code>processo genérico</code> fornecido como
	 * argumento.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo genérico que deve ser inserido no banco de dados.
	 */

	public ProcessoGenerico inserir(ProcessoGenerico processoGenerico) {
		entityManager.persist(processoGenerico);
		return processoGenerico;
	}

	/**
	 * Busca no banco de dados um processo genérico com o <code>id</code>
	 * fornecido como argumento e retorna um objeto da classe
	 * {@link ProcessoGenerico} que representa esse processo. Pode retornar
	 * <code>null</code>, caso não exista um processo genérico com o
	 * <code>id</code> fornecido.
	 * 
	 * @param id
	 *            um objeto da classe {@link Integer} (ou um valor do tipo
	 *            <code>int</code>) representando o <code>id</code> do processo
	 *            genérico que deve ser buscado no banco de dados.
	 * 
	 * @return um objeto da classe <code>ProcessoGenerico</code> representando o
	 *         processo genérico desejado, caso ele exista, ou <code>null</code>
	 *         , caso não.
	 */

	public ProcessoGenerico buscar(Integer id) {
		return entityManager.find(ProcessoGenerico.class, id);
	}

	/**
	 * Atualiza no banco de dados o <code>processo genérico</code> fornecido
	 * como argumento.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo genérico que deve ser atualizado no banco de dados.
	 */

	public void atualizar(ProcessoGenerico processoGenerico) {
		entityManager.merge(processoGenerico);
	}

	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link ProcessoGenerico} . Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>ProcessoGenerico</code>
	 *         encontrados. Essa lista pode ser vazia (sem objetos), caso nenhum
	 *         registro seja encontrado.
	 * 
	 * @see ProcessoGenerico
	 * @see List
	 */

	public List<ProcessoGenerico> listarTodos() {
		return entityManager.createNamedQuery(ProcessoGenerico.LISTAR_TODOS,
				ProcessoGenerico.class).getResultList();
	}

	/**
	 * Remove do banco de dados o <code>processo genérico</code> fornecido como
	 * argumento.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo genérico que deve ser removido do banco de dados.
	 */

	public void remover(ProcessoGenerico processoGenerico) {
		entityManager.remove(entityManager.find(ProcessoGenerico.class,
				processoGenerico.getId()));
	}

	// TODO Acrescentar quantidade de processos genéricos?

}
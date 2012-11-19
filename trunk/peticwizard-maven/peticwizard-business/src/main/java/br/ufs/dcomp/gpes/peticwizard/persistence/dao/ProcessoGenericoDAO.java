package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Subarea;

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
		definirIdFormatado(processoGenerico);
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
	 *            uma {@link String} representando o <code>id</code> do processo
	 *            genérico que deve ser buscado no banco de dados.
	 * 
	 * @return um objeto da classe <code>ProcessoGenerico</code> representando o
	 *         processo genérico desejado, caso ele exista, ou <code>null</code>
	 *         , caso não.
	 */

	public ProcessoGenerico buscar(String id) {
		TypedQuery<ProcessoGenerico> query = entityManager.createNamedQuery(
				ProcessoGenerico.BUSCAR, ProcessoGenerico.class);
		query.setParameter("idFormatado", id);
		return query.getSingleResult();
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
		ProcessoGenerico processoAntigo = buscar(processoGenerico.getId());
		if ((processoAntigo != null)
				&& (!processoGenerico.getSubarea().equals(
						processoAntigo.getSubarea()))) {
			atualizarProcessosSeguintesAposRemocao(processoAntigo);
			definirIdFormatado(processoGenerico);
		}
		entityManager.merge(processoGenerico);
	}

	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link ProcessoGenerico}. Retorna um objeto {@link List} tipado contendo
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
		entityManager.remove(buscar(processoGenerico.getId()));
		atualizarProcessosSeguintesAposRemocao(processoGenerico);
	}

	/**
	 * Busca no banco de dados o processo genérico com o maior <code>id</code>
	 * associado à <code>subárea</code> fornecida como argumento e retorna um
	 * objeto da classe {@link ProcessoGenerico} que representa esse processo.
	 * Pode retornar <code>null</code>, caso não exista um processo genérico
	 * cadastrado para a subárea fornecida.
	 * 
	 * @param subarea
	 *            um objeto da classe {@link Subarea} representando a subárea
	 *            cujo processo com maior <code>id</code> se deseja obter.
	 * @return um objeto da classe <code>ProcessoGenerico</code> representando o
	 *         processo genérico desejado, caso ele exista, ou <code>null</code>
	 *         , não exista um processo genérico cadastrado para a subárea
	 *         fornecida.
	 */

	private ProcessoGenerico processoDeMaiorId(Subarea subarea) {
		TypedQuery<ProcessoGenerico> query = entityManager
				.createQuery(
						"SELECT p FROM ProcessoGenerico p WHERE p.subarea = :subarea ORDER BY p.idFormatado DESC",
						ProcessoGenerico.class);
		try {
			return query.setParameter("subarea", subarea).setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Define o <code>id</code> de um <code>processo genérico</code> como sendo
	 * o incremento do <code>id</code> do último processo cadastrado na mesma
	 * subárea. Esse método deve ser invocado sempre que um novo processo
	 * genérico for cadastrado em uma subárea ou depois que a subárea de um
	 * processo for alterada.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo genérico cujo <code>id</code> se deseja definir. O
	 *            <code>id</code> desse processo será igual ao <code>id</code>
	 *            do último processo associado à mesma subárea acrescido de 1.
	 * 
	 * @see ProcessoGenericoDAO#atualizarProcessosSeguintesAposRemocao(ProcessoGenerico)
	 */

	private void definirIdFormatado(ProcessoGenerico processoGenerico) {
		ProcessoGenerico processoDeMaiorId = processoDeMaiorId(processoGenerico
				.getSubarea());
		String id;
		if (processoDeMaiorId != null)
			id = processoGenerico.getSubarea().getId()
					+ "."
					+ (Integer
							.parseInt(processoDeMaiorId.getId().split("\\.")[2]) + 1);
		else
			id = processoGenerico.getSubarea().getId() + ".1";
		processoGenerico.setId(id);
	}

	/**
	 * Decrementa o <code>id</code> de todos os processos genéricos associados à
	 * mesma subárea de um <code>processo genérico</code> e que aparecem após
	 * este na ordem de inserção, depois que este é removido de sua subárea.
	 * Esse método deve ser invocado sempre que um processo genérico for
	 * removido ou antes que a subárea de um processo seja alterada.
	 * 
	 * @param processoRemovido
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo genérico removido de uma subárea, a partir do qual,
	 *            respeitando a ordem de inserção, os <code>id</code>s de todos
	 *            os processos genéricos associados à mesma subárea devem ser
	 *            decrementados.
	 * 
	 * @see ProcessoGenericoDAO#definirIdFormatado(ProcessoGenerico)
	 */

	private void atualizarProcessosSeguintesAposRemocao(
			ProcessoGenerico processoRemovido) {
		ProcessoGenerico processoDeMaiorId = processoDeMaiorId(processoRemovido
				.getSubarea());
		if (processoDeMaiorId != null) {
			String primeiroIndice = processoRemovido.getSubarea().getId()
					+ "."
					+ (Integer
							.parseInt(processoRemovido.getId().split("\\.")[2]) + 1);
			String ultimoIndice = processoDeMaiorId.getId();
			TypedQuery<ProcessoGenerico> query = entityManager.createQuery(
					"SELECT p FROM ProcessoGenerico p "
							+ "WHERE p.subarea = :subarea "
							+ "AND p.idFormatado >= :primeiroIndice "
							+ "AND p.idFormatado <= :ultimoIndice "
							+ "ORDER BY p.idFormatado", ProcessoGenerico.class);
			query.setParameter("subarea", processoRemovido.getSubarea());
			query.setParameter("primeiroIndice", primeiroIndice);
			query.setParameter("ultimoIndice", ultimoIndice);
			List<ProcessoGenerico> processosParaAtualizar = query
					.getResultList();
			for (ProcessoGenerico processo : processosParaAtualizar) {
				String id = processo.getSubarea().getId()
						+ "."
						+ (Integer.parseInt(processo.getId().split("\\.")[2]) - 1);
				processo.setId(id);
				atualizar(processo);
			}
		}
	}
}
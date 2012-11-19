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
 * DAO (sigla de Data Access Object) � um padr�o de projeto (<i>design
 * pattern</i>) que define a separa��o das regras de neg�cio das regras de
 * acesso a banco de dados. Todas as opera��es referentes a bancos de dados,
 * tais como inser��o, consulta, atualiza��o, remo��o e listagem de registros
 * s�o realizadas por classes projetadas espec�ficas para execut�-las.
 * 
 * <p>
 * Essa classe representa um DAO espec�fico para a entidade
 * {@link ProcessoGenerico}. Um objeto dessa classe deve ser utilizado sempre
 * que for necess�rio realizar opera��es referentes a banco envolvendo um objeto
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
	 * Construtor padr�o da classe {@link ProcessoGenericoDAO}. Retorna um
	 * objeto da classe <code>ProcessoGenericoDAO</code> capaz de gerenciar no
	 * banco de dados objetos correspondentes � entidade
	 * {@link ProcessoGenerico}.
	 * 
	 * @deprecated Deve-se utilizar inje��o para construir objetos dessa classe,
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
	 * Insere no banco de dados o <code>processo gen�rico</code> fornecido como
	 * argumento.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo gen�rico que deve ser inserido no banco de dados.
	 */

	public ProcessoGenerico inserir(ProcessoGenerico processoGenerico) {
		definirIdFormatado(processoGenerico);
		entityManager.persist(processoGenerico);
		return processoGenerico;
	}

	/**
	 * Busca no banco de dados um processo gen�rico com o <code>id</code>
	 * fornecido como argumento e retorna um objeto da classe
	 * {@link ProcessoGenerico} que representa esse processo. Pode retornar
	 * <code>null</code>, caso n�o exista um processo gen�rico com o
	 * <code>id</code> fornecido.
	 * 
	 * @param id
	 *            uma {@link String} representando o <code>id</code> do processo
	 *            gen�rico que deve ser buscado no banco de dados.
	 * 
	 * @return um objeto da classe <code>ProcessoGenerico</code> representando o
	 *         processo gen�rico desejado, caso ele exista, ou <code>null</code>
	 *         , caso n�o.
	 */

	public ProcessoGenerico buscar(String id) {
		TypedQuery<ProcessoGenerico> query = entityManager.createNamedQuery(
				ProcessoGenerico.BUSCAR, ProcessoGenerico.class);
		query.setParameter("idFormatado", id);
		return query.getSingleResult();
	}

	/**
	 * Atualiza no banco de dados o <code>processo gen�rico</code> fornecido
	 * como argumento.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo gen�rico que deve ser atualizado no banco de dados.
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
	 * Busca no banco de dados registros correspondentes � entidade
	 * {@link ProcessoGenerico}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes � entidade <code>ProcessoGenerico</code>
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
	 * Remove do banco de dados o <code>processo gen�rico</code> fornecido como
	 * argumento.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo gen�rico que deve ser removido do banco de dados.
	 */

	public void remover(ProcessoGenerico processoGenerico) {
		entityManager.remove(buscar(processoGenerico.getId()));
		atualizarProcessosSeguintesAposRemocao(processoGenerico);
	}

	/**
	 * Busca no banco de dados o processo gen�rico com o maior <code>id</code>
	 * associado � <code>sub�rea</code> fornecida como argumento e retorna um
	 * objeto da classe {@link ProcessoGenerico} que representa esse processo.
	 * Pode retornar <code>null</code>, caso n�o exista um processo gen�rico
	 * cadastrado para a sub�rea fornecida.
	 * 
	 * @param subarea
	 *            um objeto da classe {@link Subarea} representando a sub�rea
	 *            cujo processo com maior <code>id</code> se deseja obter.
	 * @return um objeto da classe <code>ProcessoGenerico</code> representando o
	 *         processo gen�rico desejado, caso ele exista, ou <code>null</code>
	 *         , n�o exista um processo gen�rico cadastrado para a sub�rea
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
	 * Define o <code>id</code> de um <code>processo gen�rico</code> como sendo
	 * o incremento do <code>id</code> do �ltimo processo cadastrado na mesma
	 * sub�rea. Esse m�todo deve ser invocado sempre que um novo processo
	 * gen�rico for cadastrado em uma sub�rea ou depois que a sub�rea de um
	 * processo for alterada.
	 * 
	 * @param processoGenerico
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo gen�rico cujo <code>id</code> se deseja definir. O
	 *            <code>id</code> desse processo ser� igual ao <code>id</code>
	 *            do �ltimo processo associado � mesma sub�rea acrescido de 1.
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
	 * Decrementa o <code>id</code> de todos os processos gen�ricos associados �
	 * mesma sub�rea de um <code>processo gen�rico</code> e que aparecem ap�s
	 * este na ordem de inser��o, depois que este � removido de sua sub�rea.
	 * Esse m�todo deve ser invocado sempre que um processo gen�rico for
	 * removido ou antes que a sub�rea de um processo seja alterada.
	 * 
	 * @param processoRemovido
	 *            um objeto da classe {@link ProcessoGenerico} representando o
	 *            processo gen�rico removido de uma sub�rea, a partir do qual,
	 *            respeitando a ordem de inser��o, os <code>id</code>s de todos
	 *            os processos gen�ricos associados � mesma sub�rea devem ser
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
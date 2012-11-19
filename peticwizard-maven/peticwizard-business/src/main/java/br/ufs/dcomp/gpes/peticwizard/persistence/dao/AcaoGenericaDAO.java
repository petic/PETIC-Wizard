package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;
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
 * Essa classe representa um DAO espec�fico para a entidade {@link AcaoGenerica}
 * . Um objeto dessa classe deve ser utilizado sempre que for necess�rio
 * realizar opera��es referentes a banco envolvendo um objeto da classe
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
	 * Construtor padr�o da classe {@link AcaoGenericaDAO}. Retorna um objeto da
	 * classe <code>AcaoGenericaDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes � entidade {@link AcaoGenerica}.
	 * 
	 * @deprecated Deve-se utilizar inje��o para construir objetos dessa classe,
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
	 * Insere no banco de dados a <code>a��o gen�rica</code> fornecida como
	 * argumento.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a a��o
	 *            gen�rica que deve ser inserida no banco de dados.
	 */

	public AcaoGenerica inserir(AcaoGenerica acaoGenerica) {
		definirIdFormatado(acaoGenerica);
		entityManager.persist(acaoGenerica);
		return acaoGenerica;
	}

	/**
	 * Busca no banco de dados uma a��o gen�rica com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link AcaoGenerica} que
	 * representa essa a��o. Pode retornar <code>null</code>, caso n�o exista
	 * uma a��o gen�rica com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            uma {@link String} representando o <code>id</code> da a��o
	 *            gen�rica que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>AcaoGenerica</code> representando a
	 *         a��o gen�rica desejada, caso ela exista, ou <code>null</code>,
	 *         caso n�o.
	 */

	public AcaoGenerica buscar(String id) {
		TypedQuery<AcaoGenerica> query = entityManager.createNamedQuery(
				AcaoGenerica.BUSCAR, AcaoGenerica.class);
		query.setParameter("idFormatado", id);
		return query.getSingleResult();
	}

	/**
	 * Atualiza no banco de dados a <code>a��o gen�rica</code> fornecida como
	 * argumento.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a a��o
	 *            gen�rica que deve ser atualizada no banco de dados.
	 */

	public void atualizar(AcaoGenerica acaoGenerica) {
		AcaoGenerica acaoAntiga = buscar(acaoGenerica.getId());
		if ((acaoAntiga != null)
				&& (!acaoGenerica.getProcessoGenerico().equals(
						acaoAntiga.getProcessoGenerico()))) {
			atualizarAcoesSeguintesAposRemocao(acaoAntiga);
			definirIdFormatado(acaoGenerica);
		}
		entityManager.merge(acaoGenerica);
	}

	/**
	 * Busca no banco de dados registros correspondentes � entidade
	 * {@link AcaoGenerica}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes � entidade <code>AcaoGenerica</code> encontrados.
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
	 * Remove do banco de dados a <code>a��o gen�rica</code> fornecida como
	 * argumento.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a a��o
	 *            gen�rica que deve ser removida do banco de dados.
	 */

	public void remover(AcaoGenerica acaoGenerica) {
		entityManager.remove(buscar(acaoGenerica.getId()));
		atualizarAcoesSeguintesAposRemocao(acaoGenerica);
	}

	/**
	 * Busca no banco de dados a a��o gen�rica com o maior <code>id</code>
	 * associado ao <code>processo gen�rico</code> fornecido como argumento e
	 * retorna um objeto da classe {@link AcaoGenerica} que representa essa
	 * a��o. Pode retornar <code>null</code>, caso n�o exista uma a��o gen�rica
	 * cadastrada para o processo gen�rico fornecido.
	 * 
	 * @param subarea
	 *            um objeto da classe {@link Subarea} representando a sub�rea
	 *            cujo processo com maior <code>id</code> se deseja obter.
	 * @return um objeto da classe <code>ProcessoGenerico</code> representando o
	 *         processo gen�rico desejado, caso ele exista, ou <code>null</code>
	 *         , n�o exista um processo gen�rico cadastrado para a sub�rea
	 *         fornecida.
	 */

	private AcaoGenerica acaoDeMaiorId(ProcessoGenerico processoGenerico) {
		TypedQuery<AcaoGenerica> query = entityManager
				.createQuery(
						"SELECT a FROM AcaoGenerica a WHERE a.processoGenerico = :processoGenerico ORDER BY a.idFormatado DESC",
						AcaoGenerica.class);
		try {
			return query.setParameter("processoGenerico", processoGenerico)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Define o <code>id</code> de uma <code>a��o gen�rica</code> como sendo o
	 * incremento do <code>id</code> da �ltima a��o cadastrada no mesmo processo
	 * gen�rico. Esse m�todo deve ser invocado sempre que uma nova a��o gen�rica
	 * for cadastrada em um processo gen�rico ou depois que o processo gen�rico
	 * de uma a��o for alterado.
	 * 
	 * @param acaoGenerica
	 *            um objeto da classe {@link AcaoGenerica} representando a a��o
	 *            gen�rica cujo <code>id</code> se deseja definir. O
	 *            <code>id</code> dessa a��o ser� igual ao <code>id</code> da
	 *            �ltima a��o associada ao mesmo processo gen�rico acrescido de
	 *            1.
	 * 
	 * @see AcaoGenericaDAO#atualizarAcoesSeguintesAposRemocao(AcaoGenerica)
	 */

	private void definirIdFormatado(AcaoGenerica acaoGenerica) {
		AcaoGenerica acaoDeMaiorId = acaoDeMaiorId(acaoGenerica
				.getProcessoGenerico());
		String id;
		if (acaoDeMaiorId != null)
			id = acaoGenerica.getProcessoGenerico().getId()
					+ "."
					+ (Integer.parseInt(acaoDeMaiorId.getId().split("\\.")[3]) + 1);
		else
			id = acaoGenerica.getProcessoGenerico().getId() + ".1";
		acaoGenerica.setId(id);
	}

	/**
	 * Decrementa o <code>id</code> de todas as a��es gen�ricas associadas ao
	 * mesmo processo gen�rico de uma <code>a��o gen�rica</code> e que aparecem
	 * ap�s esta na ordem de inser��o, depois que esta � removida de seu
	 * processo gen�rico. Esse m�todo deve ser invocado sempre que uma a��o
	 * gen�rica for removida ou antes que o processo gen�rico de uma a��o seja
	 * alterado.
	 * 
	 * @param acaoRemovida
	 *            um objeto da classe {@link AcaoGenerica} representando a a��o
	 *            gen�rica removida de um processo gen�rico, a partir da qual,
	 *            respeitando a ordem de inser��o, os <code>id</code>s de todas
	 *            as a��es gen�ricas associados ao mesmo processo gen�rico devem
	 *            ser decrementados.
	 * 
	 * @see AcaoGenericaDAO#definirIdFormatado(AcaoGenerica)
	 */

	private void atualizarAcoesSeguintesAposRemocao(AcaoGenerica acaoRemovida) {
		AcaoGenerica acaoDeMaiorId = acaoDeMaiorId(acaoRemovida
				.getProcessoGenerico());
		if (acaoDeMaiorId != null) {
			String primeiroIndice = acaoRemovida.getProcessoGenerico().getId()
					+ "."
					+ (Integer.parseInt(acaoRemovida.getId().split("\\.")[3]) + 1);
			String ultimoIndice = acaoDeMaiorId.getId();
			TypedQuery<AcaoGenerica> query = entityManager.createQuery(
					"SELECT a FROM AcaoGenerica a "
							+ "WHERE a.processoGenerico = :processoGenerico "
							+ "AND a.idFormatado >= :primeiroIndice "
							+ "AND a.idFormatado <= :ultimoIndice "
							+ "ORDER BY a.idFormatado", AcaoGenerica.class);
			query.setParameter("processoGenerico",
					acaoRemovida.getProcessoGenerico());
			query.setParameter("primeiroIndice", primeiroIndice);
			query.setParameter("ultimoIndice", ultimoIndice);
			List<AcaoGenerica> acoesParaAtualizar = query.getResultList();
			for (AcaoGenerica acao : acoesParaAtualizar) {
				String id = acao.getProcessoGenerico().getId() + "."
						+ (Integer.parseInt(acao.getId().split("\\.")[3]) - 1);
				acao.setId(id);
				atualizar(acao);
			}
		}
	}

}
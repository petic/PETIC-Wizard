package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.AcaoGenerica;

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
	 *            um objeto da classe {@link Integer} (ou um valor do tipo
	 *            <code>int</code>) representando o <code>id</code> da a��o
	 *            gen�rica que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>AcaoGenerica</code> representando a
	 *         a��o gen�rica desejada, caso ela exista, ou <code>null</code>,
	 *         caso n�o.
	 */

	public AcaoGenerica buscar(Integer id) {
		return entityManager.find(AcaoGenerica.class, id);
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
		entityManager.remove(entityManager.find(AcaoGenerica.class,
				acaoGenerica.getId()));
	}

}
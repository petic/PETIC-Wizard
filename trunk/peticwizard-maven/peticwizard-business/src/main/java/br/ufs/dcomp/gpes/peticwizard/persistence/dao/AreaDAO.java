package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Area;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade {@link Area}. Um
 * objeto dessa classe deve ser utilizado sempre que for necessário realizar
 * operações referentes a banco envolvendo um objeto da classe <code>Area</code>
 * .
 * </p>
 * 
 * @see Area
 */

@Stateless
public class AreaDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Construtor padrão da classe {@link AreaDAO}. Retorna um objeto da classe
	 * <code>AreaDAO</code> capaz de gerenciar no banco de dados objetos
	 * correspondentes à entidade {@link Area}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em: <code>@EJB private AreaDAO areaDao;</code>
	 * 
	 * @see Area
	 * @see EJB
	 */

	@Deprecated
	public AreaDAO() {
	}

	/**
	 * Busca no banco de dados uma ocorrência da entidade {@link Area} com o
	 * <code>id</code> fornecido como argumento e retorna um objeto da classe
	 * <code>Area</code> que representa essa ocorrência. Caso ainda não esteja
	 * registrada no banco de dados uma área com o <code>id</code> fornecido,
	 * mas esse <code>id</code> seja válido, um registro correspondente à área
	 * desejada é inserido e um objeto correspondente é retornado.
	 * 
	 * @param id
	 *            um objeto da classe {@link Integer} (ou um valor do tipo
	 *            <code>int</code>) representando o <code>id</code> da área que
	 *            deve ser obtida do banco de dados.
	 * 
	 * @return um objeto da classe <code>Area</code> representando a área
	 *         desejada, caso ela exista, ou <code>null</code>, caso não.
	 * 
	 * @see Area
	 * 
	 */

	public Area inserirOuObterArea(Integer id) {
		Area area = entityManager.find(Area.class, id);
		if (area != null) {
			return area;
		} else {
			area = new Area();
			area.setId(id);
			switch (id) {
			case 1:
				area.setDescricao("Dados");
				break;
			case 2:
				area.setDescricao("Software");
				break;
			case 3:
				area.setDescricao("Hardware");
				break;
			case 4:
				area.setDescricao("Telecomunicações");
				break;
			case 5:
				area.setDescricao("Pessoas");
				break; // TODO O que fazer caso seja passado um id que não
						// existe?
			}
			entityManager.persist(area);
		}
		return area;
	}

	/**
	 * Busca no banco de dados registros correspondentes à entidade {@link Area}
	 * . Retorna um objeto {@link List} tipado contendo todos os registros
	 * encontrados. Pode retornar uma lista vazia (sem objetos), caso nenhum
	 * registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>Area</code> encontrados. Essa
	 *         lista pode ser vazia (sem objetos), caso nenhum registro seja
	 *         encontrado.
	 * 
	 * @see Area
	 * @see List
	 */

	public List<Area> listarTodas() {
		return entityManager.createNamedQuery(Area.LISTAR_TODAS, Area.class)
				.getResultList();
	}

	/**
	 * Obtém no banco de dados a quantidade de áreas cadastradas no banco de
	 * dados.
	 * 
	 * @return a quantidade de áreas cadastradas no banco de dados.
	 */

	public int quantidade() {
		return 5; // TODO Fixar o número de áreas?
	}

}
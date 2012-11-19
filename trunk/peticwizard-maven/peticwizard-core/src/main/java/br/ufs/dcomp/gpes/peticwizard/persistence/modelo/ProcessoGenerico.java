package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

/**
 * Representa a entidade Processo Genérico definida no modelo de domínio da
 * aplicação. Configura uma entidade JPA (uma classe anotada com {@link Entity})
 * mapeada para uma tabela de nome <code>petic_processo_generico</code> no banco
 * de dados. Implementa métodos <i>getters</i> e <i>setters</i> para seus
 * atributos privados, não sendo capaz de realizar nenhuma operação referente a
 * banco de dados, como inserção, consulta, atualização, remoção e listagem de
 * registros. Para realizar tais operações deve-se utilizar um objeto da classe
 * {@link ProcessoGenericoDAO}.
 * 
 * @see Entity
 * @see ProcessoGenericoDAO
 */

@Entity
@Table(name = "petic_processo_generico")
@NamedQueries({
		@NamedQuery(name = ProcessoGenerico.LISTAR_TODOS, query = "SELECT p FROM ProcessoGenerico p ORDER BY p.idFormatado"),
		@NamedQuery(name = ProcessoGenerico.BUSCAR, query = "SELECT p FROM ProcessoGenerico p WHERE p.idFormatado = :idFormatado") })
public class ProcessoGenerico implements Serializable,
		Comparable<ProcessoGenerico> {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "ProcessoGenerico.listarTodos";
	public static final String BUSCAR = "ProcessoGenerico.buscar";

	@Id
	@GeneratedValue
	private Integer id;

	@Index(name = "idFormatadoIndex")
	@NotNull
	private String idFormatado;

	@NotNull
	private String nome;
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Subarea subarea;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processoGenerico", cascade = { CascadeType.REMOVE })
	private List<AcaoGenerica> acoesGenericas = new ArrayList<AcaoGenerica>();
	
	// TODO Campos: entradas e saídas

	public String getId() {
		return idFormatado;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Subarea getSubarea() {
		return subarea;
	}

	public List<AcaoGenerica> getAcoesGenericas() {
		return acoesGenericas;
	}

	public void setId(String id) {
		this.idFormatado = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setSubarea(Subarea subarea) {
		this.subarea = subarea;
	}

	public void setAcoesGenericas(List<AcaoGenerica> acoesGenericas) {
		this.acoesGenericas = acoesGenericas;
	}

	@Override
	public int compareTo(ProcessoGenerico outroProcesso) {
		return this.idFormatado.compareTo(outroProcesso.idFormatado);
	}

	@Override
	public boolean equals(Object obj) {
		ProcessoGenerico outroProcesso = (ProcessoGenerico) obj;
		if (outroProcesso != null) {
			return (outroProcesso.id.equals(this.id));
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return idFormatado + " - " + nome;
	}
}
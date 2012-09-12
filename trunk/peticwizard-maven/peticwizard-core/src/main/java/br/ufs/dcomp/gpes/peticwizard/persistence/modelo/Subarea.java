package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade Subárea definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_subarea</code> no banco de dados.
 * Implementa métodos <i>getters</i> e <i>setters</i> para seus atributos
 * privados, não sendo capaz de realizar nenhuma operação referente a banco de
 * dados, como inserção, consulta, atualização, remoção e listagem de registros.
 * Para realizar tais operações deve-se utilizar um objeto da classe
 * {@link SubareaDAO}.
 * 
 * @see Entity
 * @see SubareaDAO
 */

@Entity
@Table(name = "petic_subarea")
// @NamedQuery(name = Subarea.LISTAR_TODAS, query = "SELECT s FROM Subarea s")
public class Subarea implements Serializable {

	private static final long serialVersionUID = 1L;
	// public static final String LISTAR_TODAS = "Subarea.listarTodas";

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Area area;

	@NotNull
	private String descricao;

	@OneToMany(/* fetch = FetchType.LAZY, */mappedBy = "subarea", cascade = { CascadeType.REMOVE })
	private List<ProcessoGenerico> processosGenericos = new ArrayList<ProcessoGenerico>();

	public Integer getId() {
		return id;
	}

	public Area getArea() {
		return area;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<ProcessoGenerico> getProcessosGenericos() {
		return processosGenericos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

	@Override
	public boolean equals(Object obj) {
		Subarea outraSubarea = (Subarea) obj;
		if (outraSubarea != null) {
			return (outraSubarea.getId() == this.id);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id;
	}

}
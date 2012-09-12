package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade Área definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_area</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link AreaDAO}.
 * 
 * @see AreaDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_area")
@NamedQuery(name = Area.LISTAR_TODAS, query = "SELECT a FROM Area a")
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODAS = "Area.listarTodas";

	@Id
	private Integer id;

	@NotNull
	private String descricao;

	@OneToMany(/* fetch = FetchType.LAZY, */mappedBy = "area")
	private List<Subarea> subareas = new ArrayList<Subarea>();

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Subarea> getSubareas() {
		return subareas;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Area outraArea = (Area) obj;
		if (outraArea != null) {
			return (outraArea.getId() == this.id);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id;
	}

}
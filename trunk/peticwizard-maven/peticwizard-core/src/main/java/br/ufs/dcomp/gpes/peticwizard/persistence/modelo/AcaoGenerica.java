package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade A��o Gen�rica definida no modelo de dom�nio da
 * aplica��o. Configura uma entidade JPA (uma classe anotada com {@link Entity})
 * mapeada para uma tabela de nome <code>petic_acao_generica</code> no banco de
 * dados. Implementa m�todos <i>getters</i> e <i>setters</i> para seus atributos
 * privados, n�o sendo capaz de realizar nenhuma opera��o referente a banco de
 * dados, como inser��o, consulta, atualiza��o, remo��o e listagem de registros.
 * Para realizar tais opera��es deve-se utilizar um objeto tipado da classe
 * {@link AcaoGenericaDAO}.
 * 
 * @see Entity
 * @see AcaoGenericaDAO
 */

@Entity
@Table(name = "petic_acao_generica")
@NamedQuery(name = AcaoGenerica.LISTAR_TODAS, query = "SELECT a FROM AcaoGenerica a")
public class AcaoGenerica implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODAS = "AcaoGenerica.listarTodas";

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
	private String descricao;
	private String boasPraticas;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private ProcessoGenerico processoGenerico;

	// TODO Campos: dura��o m�dia, custo m�dio, esfor�o m�dio e
	// solu��es/recomenda��es

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getBoasPraticas() {
		return boasPraticas;
	}

	public ProcessoGenerico getProcessoGenerico() {
		return processoGenerico;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setBoasPraticas(String boasPraticas) {
		this.boasPraticas = boasPraticas;
	}

	public void setProcessoGenerico(ProcessoGenerico processoGenerico) {
		this.processoGenerico = processoGenerico;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		AcaoGenerica outraAcao = (AcaoGenerica) obj;
		if (outraAcao != null) {
			return (outraAcao.getId() == this.id);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id;
	}

}
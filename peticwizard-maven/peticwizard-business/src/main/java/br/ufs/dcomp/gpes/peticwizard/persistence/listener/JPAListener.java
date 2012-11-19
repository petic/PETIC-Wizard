package br.ufs.dcomp.gpes.peticwizard.persistence.listener;

import javax.persistence.*;

/**
 * Essa classe define um listener padr�o que pode ser associado a qualquer
 * entidade JPA da aplica��o (qualquer classe anotada com {@link Entity}). Ele
 * imprime uma mensagem na sa�da padr�o ap�s o in�cio e o encerramento de cada
 * uma das fases do ciclo de vida da entidade associada, informando o nome da
 * classe da entidade e a fase do ciclo de vida no momento do processamento.
 * 
 * <p>
 * Para que uma entidade JPA seja monitorada por esse listener, ela deve ser
 * anotada com {@link EntityListeners}, como em:
 * </p>
 * <code>@EntityListeners(JPAListener.class)<br/>
 * public class Area {</code></p>
 * 
 * <p>
 * Tamb�m � poss�vel definir esse listener como um listener padr�o, usado para
 * monitorar o ciclo de vida de todas as entidades da aplica��o. Nesse caso,
 * deve-se recorrer � configura��o da unidade de persist�ncia via arquivos XML.
 * </p>
 * 
 * @see Entity
 * @see EntityListeners
 */

public class JPAListener {

	// TODO Classe utilizada apenas para depura��o. Remover da vers�o final.

	private void imprimirFase(Object object, String fase) {
		System.out.println("Entidade " + object.getClass().getSimpleName()
				+ " - " + fase);
	}

	@PrePersist
	void prePersist(Object object) {
		imprimirFase(object, "PrePersist");
	}

	@PostPersist
	void postPersist(Object object) {
		imprimirFase(object, "PostPersist");
	}

	@PreUpdate
	void preUpdate(Object object) {
		imprimirFase(object, "PreUpdate");
	}

	@PostUpdate
	void postUpdate(Object object) {
		imprimirFase(object, "PostUpdate");
	}

	@PreRemove
	void preRemove(Object object) {
		imprimirFase(object, "PreRemove");
	}

	@PostRemove
	void postRemove(Object object) {
		imprimirFase(object, "PostRemove");
	}

	@PostLoad
	void postLoad(Object object) {
		imprimirFase(object, "PostLoad");
	}
}
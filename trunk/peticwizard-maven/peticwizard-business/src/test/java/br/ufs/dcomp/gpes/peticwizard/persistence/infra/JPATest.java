package br.ufs.dcomp.gpes.peticwizard.persistence.infra;

import static org.junit.Assert.assertNotNull;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * Teste unit�rio que pode ser executado com o framework JUnit. Serve para
 * diagnosticar problemas com a configura��o da persist�ncia, com as entidades
 * JPA (classes anotadas com {@link Entity}) do projeto ou com a conex�o ao
 * banco de dados; e tamb�m para atualizar a estrutura das tabelas no banco de
 * dados para refletir os atributos e relacionamentos definidos nas entidades
 * JPA.
 * 
 * <p>
 * Durante a execu��o do teste, tenta-se criar um objeto da classe
 * {@link EntityManagerFactory}. Se nenhum problema for encontrado, a estrutura
 * das tabelas no banco de dados � atualizada, esse objeto � criado, e o teste �
 * conclu�do com sucesso.
 * </p>
 * 
 * <p>
 * Caso qualquer problema seja encontrado, o <code>EntityManagerFactory</code>
 * n�o � criado, e o teste n�o � conclu�do com sucesso.
 * </p>
 * 
 * @see Assert
 * @see Entity
 * @see EntityManagerFactory
 * @see Test
 */

public class JPATest {

	private static EntityManagerFactory emf;

	@Test
	public void atualizarBanco() throws Exception {
		emf = Persistence.createEntityManagerFactory("petic");
		assertNotNull(emf);
	}

}
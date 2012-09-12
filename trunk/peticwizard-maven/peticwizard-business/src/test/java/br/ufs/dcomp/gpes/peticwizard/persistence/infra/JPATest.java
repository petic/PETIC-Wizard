package br.ufs.dcomp.gpes.peticwizard.persistence.infra;

import static org.junit.Assert.assertNotNull;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * Teste unitário que pode ser executado com o framework JUnit. Serve para
 * diagnosticar problemas com a configuração da persistência, com as entidades
 * JPA (classes anotadas com {@link Entity}) do projeto ou com a conexão ao
 * banco de dados; e também para atualizar a estrutura das tabelas no banco de
 * dados para refletir os atributos e relacionamentos definidos nas entidades
 * JPA.
 * 
 * <p>
 * Durante a execução do teste, tenta-se criar um objeto da classe
 * {@link EntityManagerFactory}. Se nenhum problema for encontrado, a estrutura
 * das tabelas no banco de dados é atualizada, esse objeto é criado, e o teste é
 * concluído com sucesso.
 * </p>
 * 
 * <p>
 * Caso qualquer problema seja encontrado, o <code>EntityManagerFactory</code>
 * não é criado, e o teste não é concluído com sucesso.
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
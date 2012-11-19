package br.ufs.dcomp.gpes.peticwizard.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.ProcessoGenericoDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.presentation.MobiPETIC.ProcessosProtos;

import com.google.api.client.protobuf.ProtocolBuffers;

/**
 * Implementa um webservice capaz de realizar opera��es referentes � entidade
 * {@link ProcessoGenerico}: consulta de uma processo gen�rico por ID. Todos os
 * m�todos desse webservice respondem pela URL <code>/processos</code>.
 * 
 * @see ProcessoGenerico
 */

@Path("processos")
@Produces(ProtocolBuffers.CONTENT_TYPE)
public class ProcessosRESTService {

	@EJB
	private ProcessoGenericoDAO daoProcessoGenerico;

	@Context
	private UriInfo uriInfo;

	/**
	 * Implementa a consulta a um processo gen�rico espec�fico, fornecido seu
	 * <code>id</code>. Esse m�todo � invocado no servidor quando o cliente
	 * requisita o endere�o <code>/processos/id</code>, em que <code>id</code>
	 * corresponde ao <code>id</code> do processo gen�rico que se deseja
	 * consultar (por exemplo, <code>/processos/1.1.1</code>), usando o m�todo
	 * HTTP {@link GET}.
	 * 
	 * @param id
	 *            corresponde ao <code>id</code> do processo gen�rico que se
	 *            deseja consultar (por exemplo, <code>/processos/1.1.1</code>).
	 * @return uma mensagem do Protocol Buffers do tipo
	 *         {@link ProcessosProtos.Processo} , representando um processo
	 *         gen�rico cadastrado na base de dados.
	 */

	@GET
	@Path("{id}")
	public ProcessosProtos.Processo consultarProcesso(@PathParam("id") String id) {
		ProcessoGenerico processoJPA = daoProcessoGenerico.buscar(id);
		if (processoJPA == null) {
			return ProcessosProtos.Processo.newBuilder().setId("0").setNome("")
					.build();
		} else {
			ProcessosProtos.Processo.Builder processoPB = ProcessosProtos.Processo
					.newBuilder().setId(processoJPA.getId().toString())
					.setNome(processoJPA.getNome())
					.setSubareaId(processoJPA.getSubarea().getId());
			if (processoJPA.getDescricao() != null)
				processoPB = processoPB
						.setDescricao(processoJPA.getDescricao());
			return processoPB.build();
		}
	}
}
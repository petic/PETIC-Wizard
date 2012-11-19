package br.ufs.dcomp.gpes.peticwizard.rest;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.SubareaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.ProcessoGenerico;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Subarea;
import br.ufs.dcomp.gpes.peticwizard.presentation.MobiPETIC.ProcessosProtos;
import br.ufs.dcomp.gpes.peticwizard.presentation.MobiPETIC.SubareasProtos;

import com.google.api.client.protobuf.ProtocolBuffers;

/**
 * Implementa um webservice capaz de realizar opera��es referentes � entidade
 * {@link Subarea}: consulta de uma sub�rea por ID e listagem de todas os
 * processos gen�ricos de uma sub�rea. Todos os m�todos desse webservice
 * respondem pela URL <code>/subareas</code>.
 * 
 * <p>
 * Essa classe � anotada com {@link Stateful} e {@link PersistenceContext} para
 * evitar a ocorr�ncia da {@link LazyInitializationException} (ver explica��o em
 * <a href=
 * "http://ocpsoft.org/opensource/creating-a-facebook-app-the-webservice-and-the-game/#section-2"
 * >Creating a Facebook App with Java - Part 3 - The Web Service and the
 * Game</a>).
 * </p>
 * 
 * @see Subarea
 */

@Path("subareas")
@Stateful
@PersistenceContext(name = "petic", type = PersistenceContextType.EXTENDED)
@Produces(ProtocolBuffers.CONTENT_TYPE)
public class SubareasRESTService {

	@EJB
	private SubareaDAO daoSubarea;

	@Context
	private UriInfo uriInfo;

	/**
	 * Implementa a consulta a uma sub�rea espec�fica, fornecido seu
	 * <code>id</code>. Esse m�todo � invocado no servidor quando o cliente
	 * requisita o endere�o <code>/subareas/id</code>, em que <code>id</code>
	 * corresponde ao <code>id</code> da sub�rea que se deseja consultar (por
	 * exemplo, <code>/subareas/1.1</code>), usando o m�todo HTTP {@link GET}.
	 * 
	 * @param id
	 *            uma {@link String} representando o <code>id</code> da sub�rea
	 *            que se deseja consultar (por exemplo,
	 *            <code>/subareas/1.1</code>).
	 * @return uma mensagem do Protocol Buffers do tipo
	 *         {@link SubareasProtos.Subarea}, representando uma sub�rea
	 *         cadastrada na base de dados.
	 */

	@GET
	@Path("{id}")
	public SubareasProtos.Subarea consultarSubarea(@PathParam("id") String id) {
		Subarea subareaJPA = daoSubarea.buscar(id);
		if (subareaJPA == null) {
			return SubareasProtos.Subarea.newBuilder().setId("0").setNome("")
					.build();
		} else {
			return SubareasProtos.Subarea.newBuilder()
					.setId(subareaJPA.getId())
					.setNome(subareaJPA.getDescricao())
					.setAreaId(subareaJPA.getArea().getId()).build();
		}
	}

	/**
	 * Implementa a listagem de todos os processos gen�ricos de determinada
	 * sub�rea, fornecido seu <code>id</code>. Esse m�todo � invocado no
	 * servidor quando o cliente requisita o endere�o
	 * <code>/subareas/id/processos</code>, em que <code>id</code> corresponde
	 * ao <code>id</code> da sub�rea cujos processos gen�ricos se deseja listar
	 * (por exemplo, <code>/subareas/1.1/processos</code>), usando o m�todo HTTP
	 * {@link GET}.
	 * 
	 * @param id
	 *            corresponde ao <code>id</code> da sub�rea cujos processos
	 *            gen�ricos se deseja listar (por exemplo,
	 *            <code>/subareas/1.1/processos</code>).
	 * 
	 * @return uma mensagem do Protocol Buffers do tipo
	 *         {@link ProcessosProtos.Processos}, da qual pode ser obtida uma
	 *         lista de {@link ProcessosProtos.Processo}, cada um representando
	 *         um processo gen�rico relacionado � sub�rea de <code>id</code>
	 *         fornecido.
	 */

	@GET
	@Path("{id}/processos")
	public ProcessosProtos.Processos listarTodosOsProcessos(
			@PathParam("id") String id) {
		Subarea subareaJPA = daoSubarea.buscar(id);
		ProcessosProtos.Processos.Builder processosPB = ProcessosProtos.Processos
				.newBuilder();
		for (ProcessoGenerico processoJPA : subareaJPA.getProcessosGenericos()) {
			ProcessosProtos.Processo.Builder processoPB = ProcessosProtos.Processo
					.newBuilder().setId(processoJPA.getId().toString())
					.setNome(processoJPA.getNome())
					.setSubareaId(processoJPA.getSubarea().getId());
			if (processoJPA.getDescricao() != null)
				processoPB = processoPB
						.setDescricao(processoJPA.getDescricao());
			processosPB = processosPB.addProcesso(processoPB.build());
		}
		return processosPB.build();
	}
}
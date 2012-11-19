package br.ufs.dcomp.gpes.peticwizard.rest;

import java.util.List;

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

import br.ufs.dcomp.gpes.peticwizard.persistence.dao.AreaDAO;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Area;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Subarea;
import br.ufs.dcomp.gpes.peticwizard.presentation.MobiPETIC.AreasProtos;
import br.ufs.dcomp.gpes.peticwizard.presentation.MobiPETIC.SubareasProtos;

import com.google.api.client.protobuf.ProtocolBuffers;

/**
 * Implementa um webservice capaz de realizar opera��es referentes � entidade
 * {@link Area}: listagem de todas as �reas, consulta de uma �rea por ID e
 * listagem de todas as sub�reas de uma �rea. Todos os m�todos desse webservice
 * respondem pela URL "/areas".
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
 * @see Area
 */

@Path("areas")
@Stateful
@PersistenceContext(name = "petic", type = PersistenceContextType.EXTENDED)
@Produces(ProtocolBuffers.CONTENT_TYPE)
public class AreasRESTService {

	@EJB
	private AreaDAO daoArea;

	@Context
	private UriInfo uriInfo;

	/**
	 * Implementa a listagem de �reas, que � retornada ao cliente quando o mesmo
	 * requisita o endere�o <code>/areas</code> usando o m�todo HTTP {@link GET}
	 * .
	 * 
	 * @return uma mensagem do Protocol Buffers do tipo
	 *         {@link AreasProtos.Areas}, da qual pode ser obtida uma lista de
	 *         {@link AreasProtos.Area}, cada uma representando uma �rea
	 *         cadastrada na base de dados.
	 */

	@GET
	public AreasProtos.Areas listarTodasAsAreas() {
		List<Area> areasJPA = daoArea.listarTodas();
		AreasProtos.Areas.Builder areasPB = AreasProtos.Areas.newBuilder();
		for (Area areaJPA : areasJPA) {
			areasPB = areasPB.addArea(AreasProtos.Area.newBuilder()
					.setId(areaJPA.getId().toString())
					.setNome(areaJPA.getDescricao()).build());
		}
		return areasPB.build();
	}

	/**
	 * Implementa a consulta a uma �rea espec�fica, fornecido seu
	 * <code>id</code>. Esse m�todo � invocado no servidor quando o cliente
	 * requisita o endere�o <code>/areas/id</code>, em que <code>id</code>
	 * corresponde ao <code>id</code> da �rea que se deseja consultar (por
	 * exemplo, <code>/areas/1</code>), usando o m�todo HTTP {@link GET}.
	 * 
	 * @param id
	 *            corresponde ao <code>id</code> da �rea que se deseja consultar
	 *            (por exemplo, <code>/areas/1</code>).
	 * @return uma mensagem do Protocol Buffers do tipo {@link AreasProtos.Area}
	 *         , representando uma �rea cadastrada na base de dados.
	 */

	@GET
	@Path("{id}")
	public AreasProtos.Area consultarArea(@PathParam("id") int id) {
		Area areaJPA = daoArea.inserirOuObterArea(id);
		if (areaJPA == null) {
			return AreasProtos.Area.newBuilder().setId("0").setNome("").build();
		} else {
			return AreasProtos.Area.newBuilder()
					.setId(areaJPA.getId().toString())
					.setNome(areaJPA.getDescricao()).build();
		}
	}

	/**
	 * Implementa a listagem de todas as sub�reas de determinada �rea, fornecido
	 * seu <code>id</code>. Esse m�todo � invocado no servidor quando o cliente
	 * requisita o endere�o <code>/areas/id/subareas</code>, em que
	 * <code>id</code> corresponde ao <code>id</code> da �rea cujas sub�reas se
	 * deseja listar (por exemplo, <code>/areas/1/subareas</code>), usando o
	 * m�todo HTTP {@link GET}.
	 * 
	 * @param id
	 *            corresponde ao <code>id</code> da �rea cujas sub�reas se
	 *            deseja listar (por exemplo, <code>/areas/1/subareas</code>).
	 * 
	 * @return uma mensagem do Protocol Buffers do tipo
	 *         {@link SubareasProtos.Subareas}, da qual pode ser obtida uma
	 *         lista de {@link SubareasProtos.Subarea}, cada uma representando
	 *         uma sub�rea relacionada � �rea de <code>id</code> fornecido.
	 */

	@GET
	@Path("{id}/subareas")
	public SubareasProtos.Subareas listarTodasAsSubareas(@PathParam("id") int id) {
		Area areaJPA = daoArea.inserirOuObterArea(id);
		SubareasProtos.Subareas.Builder subareasPB = SubareasProtos.Subareas
				.newBuilder();
		if (areaJPA != null) {
			for (Subarea subareaJPA : areaJPA.getSubareas()) {
				subareasPB = subareasPB.addSubarea(SubareasProtos.Subarea
						.newBuilder().setId(subareaJPA.getId())
						.setNome(subareaJPA.getDescricao())
						.setAreaId(areaJPA.getId()).build());
			}
		}
		return subareasPB.build();
	}
}
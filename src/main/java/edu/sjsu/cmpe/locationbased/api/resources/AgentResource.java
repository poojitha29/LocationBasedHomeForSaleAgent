package edu.sjsu.cmpe.locationbased.api.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.locationbased.domain.Agent;
import edu.sjsu.cmpe.locationbased.dto.LinkDto;
import edu.sjsu.cmpe.locationbased.dto.LinksDto;
//import edu.sjsu.cmpe.customerfeedback.dto.AgentDto;
import edu.sjsu.cmpe.locationbased.repository.AgentRepositoryInterface;

@Path("agents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AgentResource {

	private final AgentRepositoryInterface agentRepository;
	public AgentResource(AgentRepositoryInterface agentRepository) {
		// TODO Auto-generated constructor stub
		this.agentRepository = agentRepository;
	}
	
	@POST
	@Timed(name = "create-agent")
	public Response createAgent(@Valid Agent request) {
		Agent savedAgent = agentRepository.saveAgent(request);
		Long agentId = savedAgent.getAgentId();
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-agent", "/agents/"+agentId, "GET"));
		return Response.status(201).entity(links).build();		
	}
	
	/*@GET
	@Path("/{agentId}")
	@Timed(name = "view-agent")
	public Response viewAgent(@PathParam("agentId") int agentId) {
		Agent agent = agentRepository.getAgentbyAgentID(agentId);
		AgentDto links = new AgentDto(agent);
		links.addLink(new LinkDto("view-products-by-agent", "/agents/"+agentId+"/products", "GET"));		
		return Response.ok().entity(links).build();
	}*/
}
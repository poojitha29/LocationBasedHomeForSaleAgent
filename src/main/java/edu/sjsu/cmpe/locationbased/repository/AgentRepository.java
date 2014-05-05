package edu.sjsu.cmpe.locationbased.repository;

import static com.google.common.base.Preconditions.checkNotNull;




import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.locationbased.domain.Agent;


public class AgentRepository implements AgentRepositoryInterface{
	private final ConcurrentHashMap<Long, Agent> agentInMemoryMap;
	private long agentId;
	
	public AgentRepository(ConcurrentHashMap<Long, Agent> agentMap) {
		checkNotNull(agentMap, "agentMap must not be null for AgentRepository");
		agentInMemoryMap = agentMap;
		agentId = 0;	
		
	}
	
	private final Long generateAgentId()	{
		return Long.valueOf(++agentId);
	}
/// register
	//@Override
	public Agent saveAgent(Agent newAgent) {
		checkNotNull(newAgent, "newAgent instance cannot be null");
		Long id = generateAgentId();
		newAgent.setAgentId(id);		
		agentInMemoryMap.putIfAbsent(id, newAgent);
		return newAgent;
	}

	/*@Override
	public Agent getAgentbyAgentID(int agentId) {
		checkArgument(agentId>0,"agentId was "+agentId+", but expected a greater than zero value");
		return agentInMemoryMap.get(agentId);
		
	}*/
	//// login
	//@Override
	public Agent getAgentbyAgentName(String agentName) {
		checkNotNull(agentName, "Agent Name cannot be null");
		//Agent tempAgent = new Agent();
		return agentInMemoryMap.get(agentName);
		//return tempAgent;
	}
}

package edu.sjsu.cmpe.locationbased.repository;

import edu.sjsu.cmpe.locationbased.domain.Agent;

public interface AgentRepositoryInterface {

	Agent saveAgent( Agent newAgent);
	Agent getAgentbyAgentName(String agentName);
}

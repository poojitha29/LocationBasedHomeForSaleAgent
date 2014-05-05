package edu.sjsu.cmpe.locationbased.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.locationbased.domain.Agent;

@JsonPropertyOrder({"agent","links"})

public class AgentDto extends LinksDto{

	private Agent agent;

	public AgentDto(Agent agent) {
		this.agent = agent;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
}

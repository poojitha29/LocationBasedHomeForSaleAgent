package edu.sjsu.cmpe.locationbased.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Agent {
Long agentId;
@NotEmpty
String agentName;
@NotEmpty
String phoneNumber="";
@NotEmpty
String email;

public Agent(){}


public Long getAgentId() {
	return agentId;
}

public void setAgentId(Long agentId) {
	this.agentId = agentId;
}

public String getAgentName() {
	return agentName;
}

public void setAgentName(String agentName) {
	this.agentName = agentName;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


}

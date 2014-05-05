package edu.sjsu.cmpe.locationbased;


import java.util.concurrent.ConcurrentHashMap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;


import edu.sjsu.cmpe.locationbased.api.resources.AgentResource;
//import edu.sjsu.cmpe.locationbased.api.resources.AuthorResource;
import edu.sjsu.cmpe.locationbased.api.resources.PropertyResource;
//import edu.sjsu.cmpe.locationbased.api.resources.ReviewResource;
import edu.sjsu.cmpe.locationbased.api.resources.RootResource;
import edu.sjsu.cmpe.locationbased.config.LocationBasedHomeForSaleAgentConfiguration;
import edu.sjsu.cmpe.locationbased.domain.Property;
import edu.sjsu.cmpe.locationbased.repository.PropertyRepository;
import edu.sjsu.cmpe.locationbased.repository.PropertyRepositoryInterface;
import edu.sjsu.cmpe.locationbased.domain.Agent;
import edu.sjsu.cmpe.locationbased.repository.AgentRepository;
import edu.sjsu.cmpe.locationbased.repository.AgentRepositoryInterface;

public class LocationBased extends Service<LocationBasedHomeForSaleAgentConfiguration> {

    public static void main(String[] args) throws Exception {
	new LocationBased().run(args);
    }

    @Override
    public void initialize(Bootstrap<LocationBasedHomeForSaleAgentConfiguration> bootstrap) {
	bootstrap.setName("location-service");
    }

    @Override
    public void run(LocationBasedHomeForSaleAgentConfiguration configuration,
	    Environment environment) throws Exception {
	/** Root API */
	environment.addResource(RootResource.class);
	/** Propertys APIs */
	PropertyRepositoryInterface propertyRepository = new PropertyRepository(
		new ConcurrentHashMap<Long, Property>());
	environment.addResource(new PropertyResource(propertyRepository));
	//environment.addResource(new ReviewResource(propertyRepository));
	//environment.addResource(new AuthorResource(propertyRepository));
	
	AgentRepositoryInterface agentRepository = new AgentRepository(
			new ConcurrentHashMap<Long, Agent>());
		environment.addResource(new AgentResource(agentRepository));
	
	/** Add new resources here */
    }
}

package edu.sjsu.cmpe.locationbased.repository;

import edu.sjsu.cmpe.locationbased.domain.Property;
public interface PropertyRepositoryInterface {

	Property saveProperty( Property newProperty);
	Property getPropertyByID(Long id);
	boolean updatePropertyByID(Long id,String address);
}

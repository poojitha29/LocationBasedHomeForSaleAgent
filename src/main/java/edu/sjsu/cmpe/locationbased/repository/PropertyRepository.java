package edu.sjsu.cmpe.locationbased.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.locationbased.domain.Property;

public class PropertyRepository implements PropertyRepositoryInterface{
	private final ConcurrentHashMap<Long, Property> propertyInMemoryMap;
	private long idKey;
	
	public PropertyRepository(ConcurrentHashMap<Long, Property> propertyMap){
		checkNotNull(propertyMap, "propertyMap must not be null for PropertyRepository");
		propertyInMemoryMap = propertyMap;
		idKey = 0;
	}
	private final Long generateIDKey(){
		return Long.valueOf(++idKey);
	}
	
	public Property saveProperty(Property newProperty) {
		// TODO Auto-generated method stub
		checkNotNull(newProperty, "nePropert instance must not be null");
		Long id = generateIDKey();
		newProperty.setId(id);
		
		propertyInMemoryMap.putIfAbsent(id, newProperty);
		return newProperty;
	}
	
	
	public Property getPropertyByID(Long id) {
		
		checkArgument(id >0,"ID was %s but expected greater than zero value",id);
		return propertyInMemoryMap.get(id);
	}

	/*public boolean updatePropertyByID(Long id, String address) {
		
		if(propertyInMemoryMap.containsKey(id)){
			Property uProperty = propertyInMemoryMap.get(id);
			uProperty.setAddress(address);
			return true;
		}
		else
		return false;
	} */
	public boolean updatePropertyByID(Long id, String address){
    	Property property = new Property();
    	if (propertyInMemoryMap.containsKey(id)) {
    		property = propertyInMemoryMap.get(id);
    		property.setAddress(address);
    		propertyInMemoryMap.replace(id, property);
    		return true;
    	}
    	
    	return false;
    	}
}

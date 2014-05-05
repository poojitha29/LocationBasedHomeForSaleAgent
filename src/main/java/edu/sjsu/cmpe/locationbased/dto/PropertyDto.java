package edu.sjsu.cmpe.locationbased.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.sjsu.cmpe.locationbased.domain.Property;

@JsonPropertyOrder(alphabetic = true)
public class PropertyDto extends LinksDto {
    private Property property;

    /**
     * @param book
     */
    public PropertyDto(Property property) {
	super();
	this.property = property;
    }

    /**
     * @return the book
     */
    public Property getProperty() {
	return property;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setProperty(Property property) {
	this.property = property;
    }

	
}


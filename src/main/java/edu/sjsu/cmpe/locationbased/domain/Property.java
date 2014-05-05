package edu.sjsu.cmpe.locationbased.domain;



public class Property {
private Long id;
private String address;
private String location;
private String type;
private int zipcode;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getZipcode() {
	return zipcode;
}
public void setZipcode(int zipcode) {
	this.zipcode = zipcode;
}

}

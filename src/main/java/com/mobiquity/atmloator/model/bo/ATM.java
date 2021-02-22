package com.mobiquity.atmloator.model.bo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ATM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2168154661062763856L;
	private Address address;
	private String distance;
	private List<OpeningHours> openingHours;
	private String functionality;
	private String type;
	
	public ATM() {}
	public ATM(Address address, String distance, List<OpeningHours> openingHours, String functionality, String type) {
		super();
		this.address = address;
		this.distance = distance;
		this.openingHours = openingHours;
		this.functionality = functionality;
		this.type = type;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public List<OpeningHours> getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(List<OpeningHours> openingHours) {
		this.openingHours = openingHours;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

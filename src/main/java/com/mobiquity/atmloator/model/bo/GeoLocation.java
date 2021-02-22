package com.mobiquity.atmloator.model.bo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5136112204610939914L;
	private String lat;
	private String lng;
	
	public GeoLocation() {}
	public GeoLocation(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
}

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
public class OpeningHours implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7813078531442500233L;
	private int dayOfWeek;
	private List<Hours> hours;
	
	public OpeningHours() {	}
	public OpeningHours(int dayOfWeek, List<Hours> hours) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.hours = hours;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public List<Hours> getHours() {
		return hours;
	}
	public void setHours(List<Hours> hours) {
		this.hours = hours;
	}
	
}

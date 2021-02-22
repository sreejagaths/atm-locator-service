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
public class Hours implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3117789705615982930L;
	private String hourFrom;
	private String hourTo;
	
	public Hours() {}
	public Hours(String hourFrom, String hourTo) {
		super();
		this.hourFrom = hourFrom;
		this.hourTo = hourTo;
	}
	public String getHourFrom() {
		return hourFrom;
	}
	public void setHourFrom(String hourFrom) {
		this.hourFrom = hourFrom;
	}
	public String getHourTo() {
		return hourTo;
	}
	public void setHourTo(String hourTo) {
		this.hourTo = hourTo;
	}
	
}

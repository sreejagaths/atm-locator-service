package com.mobiquity.atmloator.model.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiquity.atmloator.model.bo.ATM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Component
@JsonInclude(Include.NON_NULL)
public class ATMResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7535531872625711803L;
	private Integer size;
	private List<ATM> atms;
	private String code;
	private String message;
	
	public ATMResponse() {}
	public ATMResponse(Integer size, List<ATM> atms) {
		super();
		this.size = size;
		this.atms = atms;
	}	
	
	public ATMResponse(Integer size, List<ATM> atms, String code, String message) {
		super();
		this.size = size;
		this.atms = atms;
		this.code = code;
		this.message = message;
	}
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public List<ATM> getAtms() {
		return atms;
	}
	public void setAtms(List<ATM> atms) {
		this.atms = atms;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	
}

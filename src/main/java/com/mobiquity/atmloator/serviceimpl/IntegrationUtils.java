package com.mobiquity.atmloator.serviceimpl;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mobiquity.atmloator.model.bo.ATM;
import com.mobiquity.atmloator.model.response.ATMResponse;

@Service
public class IntegrationUtils {
	
	@Autowired
	Environment env;
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
//	@Bean("atmResponse")
//	@EventListener(ApplicationReadyEvent.class)
	//@Async
	public ATMResponse atmResponse() {
		logger.info("Fetching ATMs from External Source...");
		ResponseEntity<String> responseEntity = new RestTemplate().exchange(env.getProperty("atms.service.api"), HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
		List<ATM> atms = getATMsList(responseEntity.getBody());
		logger.info("{} ATMs Available", atms!=null? atms.size(): 0);
		
		if(atms != null)
			return new ATMResponse(atms.size(), atms);
		else 
			return new ATMResponse(atms!=null? atms.size(): 0, atms, "500", "Unable to load ATMs at this moment. Please try again!");
	}

	public JSONArray refineATMsJson(String atmsJson) {
		String fmtedJSON = atmsJson.replaceFirst("\\)]}',", "");
		try {
			return new JSONArray(fmtedJSON);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ATM> getATMsList(String atmsJson){
		List<ATM> atmsList = null;
		JSONArray refinedJson = atmsJson != null? refineATMsJson(atmsJson): null;
		if(!ObjectUtils.isEmpty(refinedJson)) { 
			//JSONArray refinedJson = refineATMsJson(atmsJson);
			atmsList = Arrays.asList(new Gson().fromJson(refinedJson.toString(), ATM[].class));
		}
		return atmsList;
	}
}

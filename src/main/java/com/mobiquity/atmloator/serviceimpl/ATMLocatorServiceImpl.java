package com.mobiquity.atmloator.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mobiquity.atmloator.model.bo.ATM;
import com.mobiquity.atmloator.model.response.ATMResponse;
import com.mobiquity.atmloator.service.ATMLocatorService;

@Service
public class ATMLocatorServiceImpl implements ATMLocatorService {
	
	@Autowired
	@Qualifier("atmResponse")
	ATMResponse atmResponse;
	
	@Autowired
	IntegrationUtils integrationUtils;
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public static List<ATM> atms;
	public static Map<String, List<ATM>> cityVsATMs;
	
	/**
	 * Fetches All the Available ATMs.
	 * Fetches from local/application cache, if available otherwise fetches from the external source by using RestTemplate and caches it for further referencing
	 * */
	@Override
	public ATMResponse fetchATMs() {
		logger.info("Fetching all the available ATMs...");
		if(atmResponse == null) {
			logger.info("Not found in local/application cache... Fetching explicitly...");
			atmResponse = integrationUtils.atmResponse();
			logger.info("Explicit Fetch Done... {} ATMs available.",atmResponse.getSize());
		}
		return atmResponse;
	}
	
	
	/**
	 * Fetches all the available ATMs from the requested city.
	 * Fetches from the local/application cache, if available otherwise fetches from the external source by using RestTemplate and caches if for further referencing and returns the available ATMs from the requested city
	 * */
	@Override
	public ATMResponse fetchATMsByCity(String city) {
		
		logger.info("Fetching ATMs from the requested city {}", city);
		String refinedCity = city.replaceAll("\\s+", "").toUpperCase();
		List<ATM> cityATMs = null;
		
		if(cityVsATMs == null) {
			cityVsATMs = new HashMap<String, List<ATM>>();
		}
				
		if(cityVsATMs.containsKey(refinedCity)) {
			logger.info("Found in local/application cache...{} ATMs available.", cityVsATMs.get(refinedCity).size());
			cityATMs = cityVsATMs.get(refinedCity);
		}
		
		else if(atmResponse != null && atmResponse.getSize() > 0){
			logger.info("City not found in the cache... Fetching from the local/application ATMs cache...");
			populateCityVsATMsMap(refinedCity);
			cityATMs = cityVsATMs.get(refinedCity);
			logger.info("{} ATMs available from the requested {}", cityATMs != null? cityATMs.size(): 0, city);
		}else {			
			atmResponse = fetchATMs();
			if(!ObjectUtils.isEmpty(atmResponse) && atmResponse.getSize() > 0){
				populateCityVsATMsMap(refinedCity);
				cityATMs = cityVsATMs.get(refinedCity);
				logger.info("{} ATMs available from the requested {}", cityATMs != null? cityATMs.size(): 0);
			}
		}

		if(cityATMs != null)		
			return new ATMResponse(cityATMs != null? cityATMs.size(): 0, cityATMs);
		else
			return new ATMResponse(0, cityATMs, "500", "Unable to load ATMs of the requested city at this moment. Please try again!");
	}
	
	public void populateCityVsATMsMap(String refinedCity) {
		List<ATM> cityATMs = atmResponse.getAtms().parallelStream().filter(atm->refinedCity.equalsIgnoreCase(atm.getAddress().getCity().replaceAll("\\s+", ""))).collect(Collectors.toList());
		if(!ObjectUtils.isEmpty(cityATMs))
			cityVsATMs.put(refinedCity, cityATMs);	
	}
	
}

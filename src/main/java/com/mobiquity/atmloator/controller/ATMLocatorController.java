package com.mobiquity.atmloator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiquity.atmloator.model.response.ATMResponse;
import com.mobiquity.atmloator.service.ATMLocatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/atm-locator")
@Api("ATMLocator")
public class ATMLocatorController {
	@Autowired
	ATMLocatorService atmLocatorService;
	
	 /**
	  * Returns All the Available ATMs with details along with the size i.e no.of available ATMs, if available in the cache, otherwise fetches from service and caches the response and returns 
	  * */
	@GetMapping("/")
	@Cacheable(value = "atms", key="'atms'")
	@ApiOperation(nickname = "getATMs", notes = "Returns all the available ATMs", value = "Get ATMs")
	public ATMResponse getATMs(){
		return atmLocatorService.fetchATMs();
	}
	
	 /**
	  * Returns the available ATMs with details in the requested city along with the size i.e no.of available ATMs, if available in the cache, otherwise fetches from service and caches the response and returns
	  * */
	@GetMapping("/city/{city_name}")
	@Cacheable(key = "'city_'+#city", value = "cityWiseATMs")
	@ApiOperation(nickname = "getATMsByCity", notes = "Returns all the available ATMs from the given city", value = "Get ATMs By City")
	public ATMResponse getATMsByCity(@PathVariable(required = true, name = "city_name") String city){
		return atmLocatorService.fetchATMsByCity(city);
	}
	
}

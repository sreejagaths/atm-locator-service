package com.mobiquity.atmloator.controller;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mobiquity.atmloator.model.bo.ATM;
import com.mobiquity.atmloator.model.bo.Address;
import com.mobiquity.atmloator.model.bo.GeoLocation;
import com.mobiquity.atmloator.model.bo.Hours;
import com.mobiquity.atmloator.model.bo.OpeningHours;
import com.mobiquity.atmloator.model.response.ATMResponse;
import com.mobiquity.atmloator.service.ATMLocatorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ATMLocatorController.class)
@SpringBootTest
public class ATMLocatorControllerTest {
	@Autowired MockMvc mockMvc;
	@MockBean ATMLocatorService atmLocatorService;
	
	GeoLocation mockGeoLocation = new GeoLocation("52.031052", "4.286927");
	Address mockAddress = new Address("Laan van Wateringse Veld", "494", "2548 CL", "Den Haag", mockGeoLocation);
	
	Hours mockHours = new Hours("07:00", "23:00");
	OpeningHours mockOpeningHours = new OpeningHours(1, Arrays.asList(mockHours));
	
	ATM atmMock = new ATM(mockAddress, "0", Arrays.asList(mockOpeningHours), "Geldautomaat", "GELDMAAT");

	ATMResponse mockATMResponse = new ATMResponse(1, Arrays.asList(atmMock));
	
	public void getATMsByCity() {
		Mockito.when(atmLocatorService.fetchATMsByCity(Mockito.anyString())).thenReturn(mockATMResponse);
		//MockMvcRequestBuilders.get("/atm-locator/	")
	}
}
